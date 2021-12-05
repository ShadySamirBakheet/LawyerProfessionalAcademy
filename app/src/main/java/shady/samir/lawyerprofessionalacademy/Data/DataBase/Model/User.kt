package shady.samir.lawyerprofessionalacademy.Data.DataBase.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid") var id :Int,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "phone") var phone: String?,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "address") var address: String?,
    @ColumnInfo(name = "state") var state: Int?,
    @ColumnInfo(name = "area") var area: Int?,
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "type") var type: String?,
    @ColumnInfo(name = "lang") var lang: String?,
    @ColumnInfo(name = "token") var token: String?
)
