package shady.samir.lawyerprofessionalacademy.Apaters.Data.Videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import shady.samir.lawyerprofessionalacademy.Data.Model.Utlis.MediaObject
import shady.samir.lawyerprofessionalacademy.R
import kotlin.collections.ArrayList


class VideoPlayerRecyclerAdapter(
    mediaObjects: ArrayList<MediaObject>,
    requestManager: RequestManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val mediaObjects: ArrayList<MediaObject>
    private val requestManager: RequestManager

    override fun onCreateViewHolder( viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return VideoPlayerViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_video, viewGroup, false),i)
    }

    override fun onBindViewHolder( viewHolder: RecyclerView.ViewHolder, i: Int) {
        (viewHolder as VideoPlayerViewHolder).onBind(mediaObjects[i], requestManager)
    }

    init {
        this.mediaObjects = mediaObjects
        this.requestManager = requestManager
    }

    override fun getItemCount(): Int {
         return mediaObjects.size
    }
}