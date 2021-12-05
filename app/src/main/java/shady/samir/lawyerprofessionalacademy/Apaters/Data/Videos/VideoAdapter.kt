package shady.samir.lawyerprofessionalacademy.Apaters.Data.Videos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shady.samir.lawyerprofessionalacademy.R

class VideoAdapter (private val context: Context?) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_video_more, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            ////
        }
    }

    override fun getItemCount(): Int {
        return 8
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val currencyName: TextView = itemView.findViewById(R.id.currencyName)
    }
}