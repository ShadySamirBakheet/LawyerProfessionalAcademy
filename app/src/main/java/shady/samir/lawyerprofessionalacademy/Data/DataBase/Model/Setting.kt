package shady.samir.lawyerprofessionalacademy.Data.DataBase.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Setting (
        @ColumnInfo(name = "isEN") var isEN: Boolean?,
        @ColumnInfo(name = "isDark") var isDark: Boolean?,
        @ColumnInfo(name = "country") var country: String?,
        @ColumnInfo(name = "currency") var currency: String?
){
        @PrimaryKey
        @ColumnInfo(name = "id") var id :Int = 0
}