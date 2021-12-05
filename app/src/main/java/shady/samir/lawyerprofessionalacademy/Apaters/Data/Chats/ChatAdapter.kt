package shady.samir.lawyerprofessionalacademy.Apaters.Data.Chats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.lawyerprofessionalacademy.Data.Model.Chats.MessageModel
import shady.samir.lawyerprofessionalacademy.R

class ChatAdapter(var context: Context,var listMsg:ArrayList<MessageModel>):RecyclerView.Adapter<ChatAdapter.ViewHolder>() {


    ////tpyes   r,s     value   m
    ////text    1,2     type    1
    ////msg status 0 send
    ////msg status 1 receive
    ////msg status 2 readed

    override fun getItemViewType(position: Int): Int {
        var messageModel = listMsg[position]
        return if (messageModel.isReceiver){ 1 }else{ 2 }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            1->{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view: View = layoutInflater.inflate(R.layout.item_text_item_in, parent, false)
                ViewHolder(view)
            }
            2->{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view: View = layoutInflater.inflate(R.layout.item_text_item_out, parent, false)
                ViewHolder(view)
            }
            else->{
                TODO("Not Found Type")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var messageModel = listMsg[position]

        when(getItemViewType(position)){
            1->{
                holder.textMessage.text = messageModel.dataMsg
                holder.timeMsg.text = messageModel.time
                holder.userName.text = messageModel.userName
            }
            2->{
                holder.textMessage.text = messageModel.dataMsg
                holder.timeMsg.text = messageModel.time
            }
            else->{
                TODO("Not Found Type")
            }
        }

    }

    override fun getItemCount(): Int {
        return listMsg.size
    }

    inner class ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
        var userImage = itemView.findViewById<ImageView>(R.id.userImage)
        var userName = itemView.findViewById<TextView>(R.id.userName)
        var timeMsg = itemView.findViewById<TextView>(R.id.date_msg)
        var msgStatusIcon = itemView.findViewById<ImageView>(R.id.msg_status_icon)
        var textMessage = itemView.findViewById<TextView>(R.id.text_message)
    }

}