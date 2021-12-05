package shady.samir.lawyerprofessionalacademy.View.LoginSystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import shady.samir.lawyerprofessionalacademy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            startActivity(
                Intent(this, SignupActivity::class.java))
        }

        binding.btnSignin.setOnClickListener {
            startActivity(
                Intent(this, ConfirmCodeActivity::class.java))
        }

        binding.forgetPassword.setOnClickListener {
            startActivity(
                Intent(this, ForgotPasswordActivity::class.java))
        }


        val imageView = binding.imageView

        Glide.with(this)
           // .load("android.resource://" + getPackageName() + "/" + R.raw.giphy)
            .load("https://media.giphy.com/media/MC2o4YmUfCqirnwM0n/giphy.gif")
            .into(imageView);

    }
}
