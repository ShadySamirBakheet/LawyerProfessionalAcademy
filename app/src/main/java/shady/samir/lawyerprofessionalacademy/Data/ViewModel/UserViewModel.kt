package shady.samir.lawyerprofessionalacademy.Data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shady.samir.lawyerprofessionalacademy.Data.DataBase.DataBase.DataBase
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.Setting
import shady.samir.lawyerprofessionalacademy.Data.DataBase.Model.User
import shady.samir.lawyerprofessionalacademy.Data.Repositories.UserRepository

class UserViewModel(application: Application):AndroidViewModel(application) {

    private var repository : UserRepository
    val getUsers : LiveData<List<User>>
    val getSetting : LiveData<List<Setting>>

    init {
        val dataBase = DataBase.getDatabase(application.applicationContext)

        val userDao = dataBase.userDao()
        val settingDao = dataBase.settingDao()

        repository = UserRepository(userDao,settingDao)

        getUsers = repository.getUsers
        getSetting = repository.getSetting

    }

    fun getUser(id:Int)= repository.getUser(id)

    private fun addUser(user: User) = viewModelScope.launch {
        repository.addUser(user)
    }

    fun deleteAllUser() = viewModelScope.launch {
        repository.deleteAllUser()
    }

    fun addSetting(setting: Setting) = viewModelScope.launch {
        repository.addSetting(setting)
    }

    fun deleteAllSetting() = viewModelScope.launch {
        repository.deleteAllSetting()
    }

/*
    fun userLogin(loginRequest: LoginRequest): LiveData<ResultApi> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(RetrofitServices::class.java)
        val responseLiveDataResult: LiveData<ResultApi> = liveData {
            val response = retService.userLogin(loginRequest)

            if (response.isSuccessful){
                if (response.body()?.status == true){
                    saveLoginUserFun(response.body()!!.data!!)
                    emit(ResultApi(true, response.body()!!.msg, response.body()!!.data))
                }else{
                    emit(ResultApi(false, response.body()!!.msg, null))
                }
            }else{
                emit(ResultApi(false, response.message(), null))
            }
        }

        return responseLiveDataResult
    }

    private fun saveLoginUserFun(data: LoginResponse.Data) {
        val user = User(data.id,data.name,data.phone,data.email,data.address,data.stateId,data.areaId,data.image,data.type,data.lang,data.token)
        deleteAllUser()
        Values.user = user
        addUser(user)
    }
*/

}