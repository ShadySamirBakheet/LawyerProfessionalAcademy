package shady.samir.lawyerprofessionalacademy.Data.DataBase.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Dao.SettingDao
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Dao.UserDao
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.Setting
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.User

@Database(entities = arrayOf(User::class, Setting::class), version = 1)

abstract class DataBase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun settingDao(): SettingDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(
            context: Context
        ): DataBase {
            if (INSTANCE == null){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "aplexshipping"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }

    }

}
