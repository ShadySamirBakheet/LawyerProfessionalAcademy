package shady.samir.lawyerprofessionalacademy.View.Videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Videos.VideoPlayerRecyclerAdapter
import shady.samir.lawyerprofessionalacademy.Data.Model.Utlis.MediaObject
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.Utlis.Model.Resources
import shady.samir.lawyerprofessionalacademy.Utlis.Model.VerticalSpacingItemDecorator
import shady.samir.lawyerprofessionalacademy.Utlis.View.VideoPlayerRecyclerView
import java.util.ArrayList

class VideosListActivity : AppCompatActivity() {

    private var listVideo: VideoPlayerRecyclerView?=null
    private lateinit var refrech: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos_list)

        listVideo = findViewById(R.id.listVideo)
        refrech = findViewById(R.id.refrech)


        refrech.setOnRefreshListener {
            initRecyclerView()
            refrech.isRefreshing = false
        }

        initRecyclerView()
        goBack()

    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        listVideo?.setLayoutManager(layoutManager)
        val itemDecorator = VerticalSpacingItemDecorator(0)
        listVideo?.addItemDecoration(itemDecorator)
        val mediaObjects: ArrayList<MediaObject> = Resources.MEDIA_OBJECTS
        listVideo?.setMediaObjects(mediaObjects)
        val adapter =  VideoPlayerRecyclerAdapter(mediaObjects, initGlide())
        listVideo?.setAdapter(adapter)
        waitFun()
    }

    private fun initGlide(): RequestManager {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }


    private fun waitFun() {
        Handler().postDelayed({
            listVideo?.playFirst()
        }, 2000)
    }


    override fun onDestroy() {
        listVideo?.releasePlayer()
        listVideo = null
        super.onDestroy()
    }

    override fun onPause() {
        listVideo?.stopPlayer()
        listVideo = null
        super.onPause()
    }

    private fun goBack() {
        val  back_btn: ImageView = findViewById(R.id.goBack)
        back_btn.setOnClickListener {
            listVideo?.releasePlayer()
            finish()
        }
    }

    override fun onStop() {
        listVideo?.stopPlayer()
        listVideo = null
        super.onStop()
    }

    override fun onBackPressed() {
        listVideo?.releasePlayer()
        listVideo = null
        super.onBackPressed()
    }

}
