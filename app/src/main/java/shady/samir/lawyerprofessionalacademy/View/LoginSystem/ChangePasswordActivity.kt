package shady.samir.lawyerprofessionalacademy.View.LoginSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityChangePasswordBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityForgotPasswordBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityUserUpdateBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.forgetPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finish()
        }
    }
}