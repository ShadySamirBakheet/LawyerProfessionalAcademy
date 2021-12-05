package shady.samir.lawyerprofessionalacademy.Apaters.Data.Videos

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import shady.samir.lawyerprofessionalacademy.Data.Model.Utlis.MediaObject
import shady.samir.lawyerprofessionalacademy.R

class VideoPlayerViewHolder(var parent: View,var type:Int) : RecyclerView.ViewHolder(parent) {
    lateinit var requestManager: RequestManager
    var volumeControl: ImageView
    var playControl: ImageView
    var thumbnail: ImageView
    var playView: ImageView
    var progressBar: ProgressBar
    var progressVideo:ProgressBar
    var timeLeftView:TextView
    var media_container: FrameLayout

    fun onBind(mediaObject: MediaObject, requestManager: RequestManager?) {
        if (requestManager != null) {
            this.requestManager = requestManager
        }
        parent.tag = this
        this.requestManager.load(mediaObject.thumbnail).into(thumbnail)
    }

    init {
        media_container = parent.findViewById(R.id.media_container)
        thumbnail       = parent.findViewById(R.id.thumbnail)
        progressBar     = parent.findViewById(R.id.progressBar)
        volumeControl   = parent.findViewById(R.id.volume_control)
        progressVideo   = parent.findViewById(R.id.progressVideo)
        timeLeftView    = parent.findViewById(R.id.timeLeftView)
        playControl     = parent.findViewById(R.id.play_control)
        playView        = parent.findViewById(R.id.play_view)
    }
}