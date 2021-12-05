package shady.samir.lawyerprofessionalacademy.Data.Model.Chats

data class ChatMsg(
    val id: Long?,
    val senderId:Int?,
    val receiverId:Int?,
    val msgData:String?,
    val date: Long?){

    constructor() : this(null,null,null,null,null)
}