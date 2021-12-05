package shady.samir.lawyerprofessionalacademy.View.Programme

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
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
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Chats.ChatAdapter
import shady.samir.lawyerprofessionalacademy.Data.Model.Chats.MessageModel
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.Utlis.Model.Values
import shady.samir.lawyerprofessionalacademy.databinding.ActivityLiveLectureBinding
import kotlin.math.roundToInt

class LiveLectureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveLectureBinding

    var listMsg = ArrayList<MessageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLiveLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشاممكن سؤال يا باشاممكن سؤال يا باشاممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",false,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",false,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",false,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",false,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",false,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",false,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشا","05:50",true,0))
        listMsg.add(MessageModel(1,1,2,"محمد خالد","ممكن سؤال يا باشاممكن سؤال يا باشاممكن سؤال يا باشاممكن سؤال يا باشا","05:50",false,0))

        binding.messagesList.apply {
            setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(this@LiveLectureActivity)
            layoutManager.stackFromEnd = true
            this.layoutManager = layoutManager
            adapter = ChatAdapter(this@LiveLectureActivity,listMsg)
        }

        binding.goBack.setOnClickListener {
            finish()
        }

        startVideo()
    }

    private fun startVideo() {
        initFun()
    }

    var mediaUrl =  "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4"
    var postion:Long = 0

    var isFirst = true
    var isPlay = true

    enum class PlayState {
        ON, OFF
    }

    private var isStartBar: Boolean = false
    lateinit var videoPlayer: PlayerView
    lateinit var videoPlayerSrc: SimpleExoPlayer
    lateinit var dataSourceFactory: DataSource.Factory
    lateinit var progressVideo: Slider
    lateinit var progressBar: ProgressBar
    lateinit var playControl: ImageView
    lateinit var videoCaver: ImageView
    lateinit var mediaControls: RelativeLayout
    lateinit var progressVideo2: ProgressBar
    lateinit var videoCon: RelativeLayout

    var playState: PlayState = PlayState.ON

    private fun initFun() {

        videoPlayer = binding.videoPlayer
        progressVideo = binding.progressVideo
        playControl = binding.playControl
        progressBar = binding.progressBar
        videoCaver = binding.videoCaver
        videoCon = binding.videoCon
        mediaControls = binding.mediaControls
        progressVideo2 = binding.progressVideo2

        playControl.setOnClickListener {
            togglePlay()
            toggleMediaControls()
        }

        videoCon.setOnClickListener {
            if (mediaControls.visibility == View.GONE){
                mediaControls.visibility = View.VISIBLE
                toggleMediaControls()
            }
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

        /// startActivity(Intent(context,VideoViewActivity::class.java).putExtra("uri",uri))

        pepaerVideo()
        ///pepaerVideoAds()
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
            Log.d(Values.TAG, "togglePlaybackState: enabling Play.")
            setVolumePlay(PlayState.ON)
        } else if (playState == PlayState.ON) {
            Log.d(Values.TAG, "togglePlaybackState: disabling Play.")
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
            playControl.setImageResource(R.drawable.ic_play)
        } else if (playState == PlayState.ON) {
            playControl.setImageResource(R.drawable.ic_pause)
        }
        /* playControl.animate().cancel()
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
        videoPlayer.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        ///videoPlayerSrc.playWhenReady = true

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
                    progressVideo.visibility = View.VISIBLE
                    isStartBar = true
                    startBar()
                    videoCaver.visibility = View.GONE
                }else{
                    stopBar()
                    videoCaver.visibility = View.VISIBLE
                }


                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        Log.e(Values.TAG, "onPlayerStateChanged: Buffering video.")
                        progressBar.visibility = View.VISIBLE
                    }
                    Player.STATE_ENDED -> {
                        Log.d(Values.TAG, "onPlayerStateChanged: Video ended.")
                        stopBar()
                        postion = 0
                        progressBar.progress = 0
                        //videoPlayerSrc.seekTo(0)
                    }
                    Player.STATE_IDLE -> {
                        Log.d(Values.TAG, "onPlayerStateChanged: Video STATE_IDLE.")
                    }
                    Player.STATE_READY -> {
                        if (isFirst){
                            videoPlayerSrc.seekTo(postion)
                            isFirst = false
                        }
                        Log.e(Values.TAG, "onPlayerStateChanged: Ready to play.")
                        progressBar.visibility = RecyclerView.GONE
                    }
                    else -> {
                        Log.d(Values.TAG, "onPlayerStateChanged: Video else.")
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
        ///progressVideo.visibility = RecyclerView.GONE
        isStartBar = false
    }

    private fun startBar() {
        if (isStartBar && progressVideo!=null ){
            val timeNow = videoPlayerSrc.currentPosition
            val timeAll = videoPlayerSrc.duration
            val duration = (timeNow.toDouble() / timeAll.toDouble())
            val progress:Float = duration.toFloat() * 100f
            if (progress >= 0 && progress <= 100){
                progressVideo.value = progress
                progressVideo2.progress = progress.roundToInt()
            }
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

    override fun onStop() {
        videoPlayerSrc.playWhenReady = false
        videoPlayerSrc.playbackState
        super.onStop()
    }

}