package shady.samir.lawyerprofessionalacademy.Utlis.View

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.*
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Videos.VideoPlayerViewHolder
import shady.samir.lawyerprofessionalacademy.Data.Model.Utlis.MediaObject
import shady.samir.lawyerprofessionalacademy.View.Videos.VideoViewActivity
import java.util.*
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.Utlis.Model.Values

class VideoPlayerRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    // ui
    private var thumbnail: ImageView? = null
    private var volumeControl: ImageView? = null
    private var playControl: ImageView? = null
    private var playView: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var viewHolderParent: View? = null
    private var frameLayout: FrameLayout? = null
    private var progressVideo:ProgressBar? = null
    var videoSurfaceView: PlayerView?= null
    private var videoPlayer: SimpleExoPlayer? = null
    var timeLeftView: TextView?=null

    // vars
    lateinit var mediaObjects : ArrayList<MediaObject>
    private var videoSurfaceDefaultHeight = 0
    private var screenDefaultHeight = 0
    private var playPosition = -1
    private var isVideoViewAdded = false
    private var requestManager: RequestManager? = null

    var isStartBar = false

    // controlling playback state
    var volumeState: VolumeState = VolumeState.OFF
    var playState: PlayState = PlayState.OFF

    init{
        val display =
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

        val point = Point()
        display.getSize(point)
        videoSurfaceDefaultHeight = point.x
        screenDefaultHeight = point.y
        Log.d(TAG, "screenDefault: x ${point.x} : y ${point.y} ")

        videoSurfaceView = PlayerView(context)
        videoSurfaceView!!.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        initVideoPlayer()
        // Bind the player to the view.
        videoSurfaceView!!.useController = false
        videoSurfaceView!!.player = videoPlayer
        setVolumeControl(VolumeState.ON)
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == SCROLL_STATE_IDLE) {
                    Log.d(TAG, "onScrollStateChanged: called. $SCROLL_STATE_IDLE : $newState")
                    if (thumbnail != null) { // show the old thumbnail
                        thumbnail!!.visibility = VISIBLE
                    }

                    // There's a special case when the end of the list has been reached.
                    // Need to handle that with this bit of logic
                    if (!recyclerView.canScrollVertically(1) ) {
                        playVideo(2)
                    } else {
                        playVideo(1)
                    }
                }
            }


        })

        addOnChildAttachStateChangeListener(object : OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {

            }

            override fun onChildViewDetachedFromWindow(view: View) {
                if (viewHolderParent != null && viewHolderParent == view) {
                    resetVideoView()
                }
            }
        })


        videoPlayer?.addListener(object : Player.EventListener {
            override fun onTimelineChanged(
                timeline: Timeline,
                @Nullable manifest: Any?,
                reason: Int
            ) {

            }

            override fun onTracksChanged(
                trackGroups: TrackGroupArray,
                trackSelections: TrackSelectionArray
            ) {
                Log.d(TAG, "onTracksChanged: called.")
            }

            override fun onLoadingChanged(isLoading: Boolean) {
                Log.d(TAG, "onLoadingChanged: called.")
            }
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

                if (playWhenReady){
                    progressVideo?.visibility = VISIBLE
                    isStartBar = true
                    startBar()
                }else{
                    stopBar()
                }
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        Log.e(TAG, "onPlayerStateChanged: Buffering video.")
                        if (progressBar != null) {
                            progressBar!!.visibility = VISIBLE
                        }
                    }
                    Player.STATE_ENDED -> {
                        Log.d(TAG, "onPlayerStateChanged: Video ended.")
                        stopBar()
                        ///videoPlayer?.seekTo(0)
                    }
                    Player.STATE_IDLE -> {
                        Log.d(TAG, "onPlayerStateChanged: Video STATE_IDLE.")
                    }
                    Player.STATE_READY -> {
                        Log.e(TAG, "onPlayerStateChanged: Ready to play.")
                        if (progressBar != null) {
                            progressBar!!.visibility = GONE
                        }
                        if (!isVideoViewAdded) {
                            addVideoView()
                        }
                    }
                    else -> {
                        Log.d(TAG, "onPlayerStateChanged: Video else.")
                    }
                }
            }

            override fun onRepeatModeChanged(repeatMode: Int) {}
            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}
            override fun onPlayerError(error: ExoPlaybackException) {}
            override fun onPositionDiscontinuity(reason: Int) {
                Log.d(TAG, "onLoadingChanged: called. $reason")
            }
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {}
            override fun onSeekProcessed() {}
        })
    }

    private fun initVideoPlayer() {
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory =
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        // 2. Create the player
        videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)

    }

    private fun stopBar() {
        progressVideo?.visibility = GONE
        isStartBar = false
    }

    private fun startBar() {
        if (isStartBar && progressVideo!=null && timeLeftView!=null){
            val timeNow = videoPlayer?.currentPosition!!
            val timeAll = videoPlayer?.duration!!
            val duration = (timeNow.toDouble() / timeAll.toDouble())
            val progress = duration * 100f
            progressVideo?.progress = progress.toInt()
            timeLeftView?.text = getTextTime(timeAll-timeNow)
            //   Log.d(TAG, "startBar: called. $timeNow  $timeAll $duration ${progress.toInt()}")
            wiatFun()
        }else{
            isStartBar = false
        }
    }

    private fun getTextTime(timeLeft: Long): String {
        if (timeLeft in 1..6299999999){
            var string = ""
            val sec = timeLeft/1000
            val min = sec/60
            val secl = sec%60
            string = "$min:$secl"
            return string
        }else{
            return "00:00"
        }
    }

    private fun wiatFun() {
        handler.postDelayed({
            startBar()
        }, 500)
    }

    fun playFirst(){

        if (thumbnail != null) { // show the old thumbnail
            thumbnail!!.visibility = VISIBLE
        }
        Log.d(
            TAG,
            "playFirest: called start"
        )
        playVideo(3)
        Log.d(
            TAG,
            "playFirest: called end"
        )
    }

    fun playVideo(isEndOfList: Int) {
        var targetPosition: Int
        if (isEndOfList == 1) {
            val startPosition =
                (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
            var endPosition =
                (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()

            // if there is more than 2 list-items on the screen, set the difference to be 1

            // if there is more than 2 list-items on the screen, set the difference to be 1
            if (endPosition - startPosition > 1) {
                endPosition = startPosition + 1
            }

            // something is wrong. return.

            // something is wrong. return.
            if (startPosition < 0 || endPosition < 0) {
                return
            }

            // if there is more than 1 list-item on the screen

            // if there is more than 1 list-item on the screen
            targetPosition = if (startPosition != endPosition) {
                val startPositionVideoHeight = getVisibleVideoSurfaceHeight(startPosition)
                val endPositionVideoHeight = getVisibleVideoSurfaceHeight(endPosition)
                if (startPositionVideoHeight > endPositionVideoHeight) startPosition else endPosition
            } else {
                startPosition
            }
        } else if (isEndOfList == 2) {
            targetPosition = mediaObjects.size - 1
        }else{
            targetPosition = 0
        }

        // video is already playing so return
        if (targetPosition == playPosition && targetPosition > 0) {

            Log.d(
                TAG,
                "playVideo: targetPosition: $targetPosition == playPosition: $playPosition"
            )

            return
        }

        Log.d(
            TAG,
            "playVideo: targetPosition: $targetPosition and playPosition: $playPosition"
        )

        // set the position of the list-item that is to be played
        playPosition = targetPosition
        if (videoSurfaceView == null) {
            return
        }

        // remove any old surface views from previously playing videos
        videoSurfaceView!!.visibility = View.INVISIBLE
        removeVideoView(videoSurfaceView!!)
        val currentPosition =
            targetPosition - (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        Log.d(
            TAG,
            "playVideo: targetPosition: $targetPosition and playPosition: $playPosition  currentPosition  $currentPosition "
        )
        val child = getChildAt(currentPosition)?: return
        Log.d(
            TAG,
            "playVideo: targetPosition: $targetPosition and playPosition: $playPosition  currentPosition  $currentPosition $child"
        )

        if (videoPlayer != null) {
            removeVideoView(videoSurfaceView!!)
        }
        if (mediaObjects[targetPosition].type == 0){

            val holder = child.tag as VideoPlayerViewHolder
            thumbnail = holder.thumbnail
            progressBar = holder.progressBar
            timeLeftView = holder.timeLeftView
            progressVideo = holder.progressVideo
            volumeControl = holder.volumeControl
            playControl = holder.playControl
            playView = holder.playView
            viewHolderParent = holder.itemView
            requestManager = holder.requestManager
            frameLayout = holder.itemView.findViewById(R.id.media_container)
            if (videoPlayer == null) {
                initVideoPlayer()
                removeVideoView(videoSurfaceView!!)
                addVideoView()
            }
            videoSurfaceView!!.useController = false
            videoSurfaceView!!.player = videoPlayer
            ///viewHolderParent!!.setOnClickListener(videoViewClickListener)
            viewHolderParent!!.setOnClickListener{
                val mediaUrl: String = mediaObjects[targetPosition].media_url!!
                val postion: Long = videoPlayer?.currentPosition ?: 0
                context.startActivity(Intent(context, VideoViewActivity::class.java).putExtra("mediaUrl",mediaUrl).putExtra("postion",postion))
            }
            volumeControl!!.setOnClickListener(audioViewClickListener)
            playControl!!.setOnClickListener(videoViewClickListener)
            playView!!.setOnClickListener {
                if (thumbnail != null) { // show the old thumbnail
                    thumbnail!!.visibility = GONE
                }
                Log.d(
                    TAG,
                    "playVideo: on Clicked "
                )

                playVideoNow()
            }

            Log.d(
                TAG,
                "playVideo: targetPosition: $targetPosition and playPosition: $playPosition  currentPosition  $currentPosition"
            )


            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                context, Util.getUserAgent(context, "Log Apps")
            )
            val mediaUrl: String = mediaObjects[targetPosition].media_url!!

            val videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(mediaUrl))
            videoPlayer!!.prepare(videoSource)
            playVideoNow()

            Log.d(
                TAG,
                "playVideo: target position : $targetPosition :playPosition $playPosition : currentPosition $currentPosition done"
            )

        }else{
            Toast.makeText(context, "IsNotVideo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun zoomInVideo(mediaUrl: String) {

        if(videoPlayer != null){
            playState = PlayState.OFF
            videoPlayer!!.playWhenReady = false
            ///HomeFragment.viewVideo(mediaUrl)
        }

    }

    private fun playVideoNow() {
        if (videoPlayer!=null) {
            playState = PlayState.ON
            videoPlayer!!.playWhenReady = true
            playView!!.visibility = GONE
        }else{
            Toast.makeText(context, "Network Not Available", Toast.LENGTH_SHORT).show()
        }
    }


    private val videoViewClickListener = OnClickListener {
        ///context.startActivity(Intent(context,VideoViewActivity::class.java).putExtra("link",))
        togglePlay()
    }

    private val audioViewClickListener = OnClickListener { toggleVolume() }

    /**
     * Returns the visible region of the video surface on the screen.
     * if some is cut off, it will return less than the @videoSurfaceDefaultHeight
     * @param playPosition
     * @return
     */
    private fun getVisibleVideoSurfaceHeight(playPosition: Int): Int {
        val at =
            playPosition - (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        Log.d(
            TAG,
            "getVisibleVideoSurfaceHeight: at: $at"
        )
        val child = getChildAt(at) ?: return 0
        val location = IntArray(2)
        child.getLocationInWindow(location)
        return if (location[1] < 0) {
            Log.d(
                TAG,
                "getVisibleVideoSurfaceHeight: videoSurfaceDefaultHeight: $videoSurfaceDefaultHeight :location:${location.get(0)} : ${location.get(1)} : " + (location[0] + videoSurfaceDefaultHeight)
            )

            location[1] + videoSurfaceDefaultHeight
        } else {
            Log.d(
                TAG,
                "getVisibleVideoSurfaceHeight: screenDefaultHeight:$screenDefaultHeight :location:${location.get(0)} : ${location.get(1)}: " + (screenDefaultHeight - location[0])
            )
            screenDefaultHeight - location[1]
        }
    }

    // Remove the old player
    fun removeVideoView(videoView: PlayerView) {
        val parent = videoView.parent as? ViewGroup
        val index = parent?.indexOfChild(videoView)
        if (index != null) {
            if (index >= 0) {
                if (viewHolderParent != null){
                    parent.removeViewAt(index)
                    isVideoViewAdded = false
                    viewHolderParent!!.setOnClickListener(null)
                }
            }
        }
    }

    private fun addVideoView() {
        frameLayout!!.addView(videoSurfaceView)
        isVideoViewAdded = true
        videoSurfaceView!!.requestFocus()
        videoSurfaceView!!.visibility = VISIBLE
        videoSurfaceView!!.alpha = 1f
        thumbnail!!.visibility = GONE
    }

    private fun resetVideoView() {
        if (isVideoViewAdded) {
            videoSurfaceView?.let { removeVideoView(it) }
            playPosition = -1
            videoSurfaceView!!.visibility = INVISIBLE
            thumbnail!!.visibility = VISIBLE
        }
    }

    fun releasePlayer() {
        if (videoPlayer != null) {
            videoPlayer!!.release()
            videoPlayer = null
        }
        isStartBar = false
        viewHolderParent = null
    }

    fun stopPlayer() {
        if (videoPlayer != null) {
            videoPlayer!!.playWhenReady = false
        }
    }

    private fun toggleVolume() {
        if (videoPlayer != null) {
            if (volumeState == VolumeState.OFF) {
                Log.d(TAG, "togglePlaybackState: enabling volume.")
                setVolumeControl(VolumeState.ON)
            } else if (volumeState == VolumeState.ON) {
                Log.d(TAG, "togglePlaybackState: disabling volume.")
                setVolumeControl(VolumeState.OFF)
            }
        }
    }

    private fun setVolumeControl(state: VolumeState) {
        volumeState = state
        if (state == VolumeState.OFF) {
            videoPlayer!!.volume = 0f
            animateVolumeControl()
        } else if (state == VolumeState.ON) {
            videoPlayer!!.volume = 1f
            animateVolumeControl()
        }
    }

    private fun animateVolumeControl() {
        if (volumeControl != null) {
            volumeControl!!.bringToFront()
            if (volumeState == VolumeState.OFF) {
                requestManager?.load(R.drawable.ic_volume_off_grey_24dp)?.into(volumeControl!!)
            } else if (volumeState == VolumeState.ON) {
                requestManager?.load(R.drawable.ic_volume_up_grey_24dp)?.into(volumeControl!!)
            }
            volumeControl!!.animate().cancel()
            volumeControl!!.alpha = 1f
            volumeControl!!.animate()
                .alpha(0f)
                .setDuration(600).startDelay = 1000
        }
    }

    private fun togglePlay() {
        if (videoPlayer != null) {
            playView!!.visibility = GONE
            if (playState == PlayState.OFF) {
                Log.d(TAG, "togglePlaybackState: enabling Play.")
                setVolumePlay(PlayState.ON)
            } else if (playState == PlayState.ON) {
                Log.d(TAG, "togglePlaybackState: disabling Play.")
                setVolumePlay(PlayState.OFF)
            }
        }
    }

    fun setVolumePlay(state: PlayState) {
        playState = state
        if (state == PlayState.OFF) {
            videoPlayer!!.playWhenReady = false
            videoPlayer!!.playbackState
            animatePlayControl()
        } else if (state == PlayState.ON) {
            videoPlayer!!.playWhenReady = true
            videoPlayer!!.playbackState
            animatePlayControl()
        }
    }

    private fun animatePlayControl() {
        if (playControl != null) {
            playControl!!.bringToFront()
            if (playState == PlayState.OFF) {
                requestManager?.load(R.drawable.ic_pause)?.into(playControl!!)
            } else if (playState == PlayState.ON) {
                requestManager?.load(R.drawable.ic_play)?.into(playControl!!)
            }
            playControl!!.animate().cancel()
            playControl!!.alpha = 1f
            playControl!!.animate().alpha(0f)
                .setDuration(600).startDelay = 1000
        }
    }

    fun setMediaObjects(mediaObjects: List<MediaObject>) {
        this.mediaObjects = mediaObjects as ArrayList<MediaObject>
    }

    enum class VolumeState {
        ON, OFF
    }

    enum class PlayState {
        ON, OFF
    }

    companion object {
        val TAG = Values.TAG
    }
}