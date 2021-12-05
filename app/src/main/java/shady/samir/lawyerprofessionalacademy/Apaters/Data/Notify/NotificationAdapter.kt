package shady.samir.lawyerprofessionalacademy.Apaters.Data.Notify

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplexgold.aplexshipping.Adapters.Data.Home.MenuAdapter
import com.aplexgold.aplexshipping.Data.Model.Home.MenuItem
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.View.Notify.NotificationViewActivity

class NotificationAdapter (private val context: Context?) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_notify , parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            when(position){
                0->imgItem.setImageResource(R.drawable.image)
                1->imgItem.setImageResource(R.drawable.image2)
                2->imgItem.setImageResource(R.drawable.image3)
                3->imgItem.setImageResource(R.drawable.image4)
                4->imgItem.setImageResource(R.drawable.image5)
                5->imgItem.setImageResource(R.drawable.image6)
                6->imgItem.setImageResource(R.drawable.image7)
            }

            itemView.setOnClickListener {
                if (context != null) {
                    context.startActivity(Intent(context, NotificationViewActivity::class.java).putExtra("idNotify",1))
                }
            }

        }
    }


    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
       return 8
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.imgItem)
    }
}