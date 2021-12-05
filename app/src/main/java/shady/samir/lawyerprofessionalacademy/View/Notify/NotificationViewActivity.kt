package shady.samir.lawyerprofessionalacademy.View.Notify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityAboutAppBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityNotificationViewBinding

class NotificationViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationViewBinding

    var idNotify = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idNotify = intent.getIntExtra("idNotify",0)

        binding.goBack.setOnClickListener {
            finish()
        }
    }
}