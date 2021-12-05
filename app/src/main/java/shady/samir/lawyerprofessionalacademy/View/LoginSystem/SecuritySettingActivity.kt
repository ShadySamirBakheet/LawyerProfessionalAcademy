package shady.samir.lawyerprofessionalacademy.View.LoginSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityMyProgrammeBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivitySecuritySettingBinding

class SecuritySettingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecuritySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecuritySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailLay.visibility = GONE
        binding.phoneCon.visibility = GONE

        binding.btnEmail.setOnClickListener {
            if (binding.emailLay.visibility == GONE){
                binding.phoneCon.visibility = GONE
                binding.emailLay.visibility = VISIBLE
            }else{
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                binding.emailLay.visibility = GONE
                finish()
            }
        }

        binding.btnPhone.setOnClickListener {
            if (binding.phoneCon.visibility == GONE){
                binding.phoneCon.visibility = VISIBLE
                binding.emailLay.visibility = GONE
            }else{
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                binding.phoneCon.visibility = GONE
                finish()
            }
        }

        binding.goBack.setOnClickListener {
           finish()
        }

    }
}