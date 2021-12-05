package shady.samir.lawyerprofessionalacademy.Data.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Dao.SettingDao
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Dao.UserDao
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.Setting
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.User

class UserRepository(var userDao: UserDao, var settingDao: SettingDao) {

    var getUsers = userDao.getAll()

    suspend fun addUser(user: User){
        userDao.insert(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAll()
    }

    var getSetting = settingDao.getAll()

    suspend fun addSetting (setting: Setting){
        settingDao.insert(setting)
    }

    suspend fun deleteAllSetting (){
        settingDao.deleteAll()
    }

    fun getUser(id: Int) = userDao.getuser(id)

}