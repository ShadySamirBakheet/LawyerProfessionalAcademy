package shady.samir.lawyerprofessionalacademy.View.Home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import shady.samir.lawyerprofessionalacademy.Data.ViewModel.UserViewModel
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.View.App.AppSettingsActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    var isSeting = true
    var uid = 1

    private lateinit var viewmodel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        setAnimation()
        getUserFun()
        getSettingFun()
        setLocale(this,"ar")

    }

    private fun getSettingFun() {
        /*val viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)*/
        viewmodel.getSetting.observe(this,{
            if (it.size > 0){
                isSeting = true
                if (it[0].isEN == true){
                    setLocale(this,"en")
                }else{
                    setLocale(this,"ar")
                }
                if (it[0].isDark == true){
                    changeThemeFun(true)
                }else{
                    changeThemeFun(false)
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    nextFun()
                }, 2000)

            }else{
                isSeting = false
                startActivity(Intent(this, AppSettingsActivity::class.java).putExtra("isMain",true))
                finish()
            }
        })
    }

    private fun changeThemeFun(dark: Boolean) {
       /// Toast.makeText(this, "Dark = $dark", Toast.LENGTH_SHORT).show()
        if(dark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun getUserFun() {
        /*viewmodel.getUser(getUID()).observe(this, {
            if (it != null) {
                Values.user = it
                Values.Token = it.token.toString()
                Log.d("tocken",it.token.toString())
                Log.d("tocken",it.id.toString()+"  |User_${getUID()}")
                /// start services
            } else {
                /// start services
            }
        })
        uid = getUID()*/
    }

    private fun getUID(): Int {
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("login_id", 0)
    }

    private fun getUserType(): Int {
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_type", 0)
    }

    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = activity.resources
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun nextFun() {
        if (isSeting){
            if (uid > 0){
                when(getUserType()){
                    0-> startActivity(Intent(this, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                   // 2-> startActivity(Intent(this, OrderDistributionActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                   // 3-> startActivity(Intent(this, OrderDistributionActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))

                }
            }else{
                //startActivity(Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }

        }else{
            startActivity(Intent(this, AppSettingsActivity::class.java).putExtra("isMain",true))
        }
        finish()
    }

    private fun setAnimation() {
        val container = findViewById<ImageView>(R.id.startImg)
        val animation: Animation
        animation = AnimationUtils.loadAnimation(this, R.anim.in_right)
        animation.duration = 2000
        container.startAnimation(animation)
    }

}