package shady.samir.lawyerprofessionalacademy.Data.Model.Chats

data class MessageModel(
    var msgID: Int?, var senderId:Int, var receiverId:Int,
    var userName:String, var dataMsg:String, var time:String,
    var isReceiver:Boolean, var msgStatus:Int
)
/*(
    var msgID: String?, var senderId:Int, var receiverId:Int,
    var userName:String, var dataMsg:String,
    var file:String, var date: String, var time:String,
    var type:Int, var isReceiver:Boolean, var msgStatus:Int
)
*/