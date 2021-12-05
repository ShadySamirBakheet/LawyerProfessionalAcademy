package shady.samir.lawyerprofessionalacademy.Apaters.Data.Programes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.View.Notify.NotificationViewActivity
import shady.samir.lawyerprofessionalacademy.View.Videos.VideoViewActivity

class LectureAdapter(private val context: Context?, val isEnd: Boolean, val isHome: Boolean) : RecyclerView.Adapter<LectureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(
            if (isHome)R.layout.item_lecture_home else R.layout.item_lecture
            , parent, false)
        return ViewHolder(view)
    }

    val mediaUrl = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/SwipingViewPager+Tutorial.mp4"

    override fun getItemViewType(position: Int): Int {
        return if (isHome) 1 else 2
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {

            if (isEnd){

                itemView.setOnClickListener {
                    if (context != null) {
                        context.startActivity(Intent(context, VideoViewActivity::class.java).putExtra("mediaUrl",mediaUrl).putExtra("postion",0))
                    }
                }

                if (context != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        dateItem.setTextColor(context.getColor(R.color.txtColor5))
                    }
                }

                dateItem.text = "هذه المحاضرة متاحة حتي 12 مايو 2020"

            }else{
                if (context != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        dateItem.setTextColor(context.getColor(R.color.txtColor3))
                    }
                }
            }

            when(position){
                0->imgItem.setImageResource(R.drawable.image)
                1->imgItem.setImageResource(R.drawable.image2)
                2->imgItem.setImageResource(R.drawable.image3)
                3->imgItem.setImageResource(R.drawable.image4)
                4->imgItem.setImageResource(R.drawable.image5)
                5->imgItem.setImageResource(R.drawable.image6)
                6->imgItem.setImageResource(R.drawable.image7)
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
        val dateItem: TextView = itemView.findViewById(R.id.dateItem)
    }
}