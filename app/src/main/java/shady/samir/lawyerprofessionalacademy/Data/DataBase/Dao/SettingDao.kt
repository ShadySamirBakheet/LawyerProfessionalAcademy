package shady.samir.lawyerprofessionalacademy.Data.DataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.Setting

@Dao
interface SettingDao {

    @Query("SELECT * FROM Setting")
    fun getAll(): LiveData<List<Setting>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: Setting):Long

    @Query("DELETE FROM Setting")
    suspend fun deleteAll()

}