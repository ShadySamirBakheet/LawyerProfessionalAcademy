package shady.samir.lawyerprofessionalacademy.View.LoginSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import shady.samir.lawyerprofessionalacademy.View.App.ContactUsActivity
import shady.samir.lawyerprofessionalacademy.databinding.ActivityUserUpdateBinding

class UserUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener {
            finish()
        }
        binding.btnChangePassword.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
            finish()
        }
    }
}