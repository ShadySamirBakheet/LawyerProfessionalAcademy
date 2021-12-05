package shady.samir.lawyerprofessionalacademy.View.Videos

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
import com.google.android.material.slider.Slider
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.Utlis.View.VideoPlayerRecyclerView

class VideoViewFullActivity : AppCompatActivity() {

    var mediaUrl = ""
    var postion:Long = 0

    var isFirst = true

    enum class PlayState {
        ON, OFF
    }

    private var isStartBar: Boolean = false
    lateinit var videoPlayer: PlayerView
    lateinit var videoPlayerSrc: SimpleExoPlayer
    lateinit var  dataSourceFactory: DataSource.Factory
    lateinit var progressVideo: Slider
    lateinit var progressBar: ProgressBar
    lateinit var timeLeftView: TextView
    lateinit var exit: ImageView
    lateinit var playControl: ImageView
    lateinit var zoomIn: ImageView
    lateinit var videoCon: RelativeLayout
    lateinit var mediaControls: RelativeLayout
    lateinit var progressVideo2: ProgressBar

    var playState: PlayState = PlayState.ON

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view_full)

        mediaUrl = intent.getStringExtra("mediaUrl").toString()
        postion = intent.getLongExtra("postion",0)

        initFun()
    }

    private fun initFun() {

        videoPlayer = findViewById(R.id.videoPlayer)
        progressVideo = findViewById(R.id.progressVideo)
        timeLeftView = findViewById(R.id.timeLeftView)
        exit = findViewById(R.id.zoomIn)
        playControl = findViewById(R.id.play_control)
        progressBar = findViewById(R.id.progressBar)
        zoomIn = findViewById(R.id.zoomIn)
        videoCon = findViewById(R.id.videoCon)
        mediaControls = findViewById(R.id.mediaControls)
        progressVideo2 = findViewById(R.id.progressVideo2)
        progressVideo.isEnabled = false

        videoCon.setOnClickListener {
            if (mediaControls.visibility == View.GONE){
                mediaControls.visibility = View.VISIBLE
                toggleMediaControls()
            }else{
                mediaControls.visibility = View.GONE
            }
        }

        exit.setOnClickListener {
            finish()
        }

        playControl.setOnClickListener {
            togglePlay()
            toggleMediaControls()
        }

        progressVideo.addOnChangeListener { slider, value, fromUser ->

            if (fromUser){
                toggleMediaControls()
                val seekValue = videoPlayerSrc.contentDuration * (value/100)
                slider.setLabelFormatter {
                    return@setLabelFormatter getTextTime((seekValue).toLong())
                }
                videoPlayerSrc.seekTo(seekValue.toLong())
            }

        }

        pepaerVideo()

    }

    private fun toggleMediaControls() {
        mediaControls.bringToFront()
        if (playState == PlayState.OFF) {
            playControl.setImageResource(R.drawable.ic_play)
        } else if (playState == PlayState.ON) {
            playControl.setImageResource(R.drawable.ic_pause)
        }
        mediaControls.animate().cancel()
        mediaControls.alpha = 1f
        mediaControls.animate()
            .alpha(0f)
            .setDuration(3000).startDelay = 1000

        Handler().postDelayed({
            mediaControls.visibility = View.GONE
        },3000)
    }

    private fun togglePlay() {
        if (playState == PlayState.OFF) {
            Log.d(VideoPlayerRecyclerView.TAG, "togglePlaybackState: enabling Play.")
            setVolumePlay(PlayState.ON)
        } else if (playState == PlayState.ON) {
            Log.d(VideoPlayerRecyclerView.TAG, "togglePlaybackState: disabling Play.")
            setVolumePlay(PlayState.OFF)
        }
    }

    private fun setVolumePlay(state: PlayState) {
        playState = state
        if (state == PlayState.OFF) {
            videoPlayerSrc.playWhenReady = false
            videoPlayerSrc.playbackState
            animatePlayControl()
        } else if (state == PlayState.ON) {
            videoPlayerSrc.playWhenReady = true
            videoPlayerSrc.playbackState
            animatePlayControl()
        }
    }

    private fun animatePlayControl() {
        playControl.bringToFront()
        if (playState == PlayState.OFF) {
            playControl.setImageResource(R.drawable.ic_pause)
        } else if (playState == PlayState.ON) {
            playControl.setImageResource(R.drawable.ic_play)
        }
        /*     playControl.animate().cancel()
             playControl.alpha = 1f
             playControl.animate()
                 .alpha(0f)
                 .setDuration(600).startDelay = 1000*/
    }

    private fun runVideo() {
        progressVideo.isEnabled = true
        val videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(mediaUrl))
        videoPlayerSrc.prepare(videoSource)
        videoPlayer.player = videoPlayerSrc
        videoPlayer.useController = false
        videoPlayer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        videoPlayerSrc.playWhenReady = true

        videoPlayerSrc.addListener(object : Player.EventListener{
            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTracksChanged(
                trackGroups: TrackGroupArray?,
                trackSelections: TrackSelectionArray?
            ) {
                // TODO("Not yet implemented")
            }

            override fun onLoadingChanged(isLoading: Boolean) {
                //TODO("Not yet implemented")
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

                if (playWhenReady){
                    progressVideo.visibility = RecyclerView.VISIBLE
                    isStartBar = true
                    startBar()
                }else{
                    stopBar()
                }


                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        Log.e(VideoPlayerRecyclerView.TAG, "onPlayerStateChanged: Buffering video.")
                        progressBar.visibility = RecyclerView.VISIBLE
                    }
                    Player.STATE_ENDED -> {
                        Log.d(VideoPlayerRecyclerView.TAG, "onPlayerStateChanged: Video ended.")
                        stopBar()
                        ///videoPlayerSrc.seekTo(0)
                    }
                    Player.STATE_IDLE -> {
                        Log.d(VideoPlayerRecyclerView.TAG, "onPlayerStateChanged: Video STATE_IDLE.")
                    }
                    Player.STATE_READY -> {
                        if (isFirst){
                            videoPlayerSrc.seekTo(postion)
                            isFirst = false
                        }
                        Log.e(VideoPlayerRecyclerView.TAG, "onPlayerStateChanged: Ready to play.")
                        progressBar.visibility = RecyclerView.GONE
                    }
                    else -> {
                        Log.d(VideoPlayerRecyclerView.TAG, "onPlayerStateChanged: Video else.")
                    }
                }
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                //TODO("Not yet implemented")
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                //TODO("Not yet implemented")
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
                //TODO("Not yet implemented")
            }

            override fun onPositionDiscontinuity(reason: Int) {
                //TODO("Not yet implemented")
            }

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
                //TODO("Not yet implemented")
            }

            override fun onSeekProcessed() {
                //TODO("Not yet implemented")
            }
        })

    }

    private fun stopBar() {
        //progressVideo.visibility = RecyclerView.GONE
        isStartBar = false
    }

    private fun startBar() {
        if (isStartBar && progressVideo!=null && timeLeftView!=null){
            val timeNow = videoPlayerSrc.currentPosition
            val timeAll = videoPlayerSrc.duration
            val duration = (timeNow.toDouble() / timeAll.toDouble())
            val progress = duration * 100f
            if (progress >= 0 && progress <= 100){
                progressVideo.value = progress.toFloat()
            }
            timeLeftView.text = getTextTime(timeAll-timeNow)
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
        val handler = Handler()
        handler.postDelayed({
            startBar()
        }, 500)
    }

    private fun pepaerVideo() {

        dataSourceFactory = DefaultDataSourceFactory(
            this, Util.getUserAgent(this, "Log Apps")
        )
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory =
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        videoPlayerSrc = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        runVideo()
    }

    override fun onPause() {
        videoPlayerSrc.playWhenReady = false
        videoPlayerSrc.playbackState
        super.onPause()
    }

    override fun onBackPressed() {
        videoPlayerSrc.playWhenReady = false
        videoPlayerSrc.playbackState
        super.onBackPressed()
    }
}