package shady.samir.lawyerprofessionalacademy.View.LoginSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import shady.samir.lawyerprofessionalacademy.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSignup.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

    }
}