package shady.samir.lawyerprofessionalacademy.View.App

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import shady.samir.lawyerprofessionalacademy.databinding.ActivityContactUsBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityHomeBinding

class ContactUsActivity : AppCompatActivity() {

    var messanger = "https://www.messenger.com/t/2118476798430137"
    var facebook = "https://www.facebook.com/AplexEgypt/"
    var mail = "info@eye-law.net"
    var host = "http://eye-law.net"
    var phone = "+20233825252"
    var youtube = "https://www.youtube.com/channel/UCCghB95SxXhPA0tPPWq8l8w"

    private lateinit var binding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneCon.setOnClickListener {
            // callNumber()
        }

        binding.emailCon.setOnClickListener {
            send()
        }

        binding.whatsAppCon.setOnClickListener {
            openWhatsApp()
        }

        binding.hostCon.setOnClickListener {
            openLink(host)
        }

        binding.youtubeCon.setOnClickListener {
            openLink(youtube)
        }

        binding.messangerCon.setOnClickListener {
            openLink(messanger)
        }

        binding.facebookCon.setOnClickListener {
            openLink(facebook)
        }

        binding.goBack.setOnClickListener {
            finish()
        }
    }

    private fun callNumber() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" +phone)
        startActivity(callIntent)
    }

    private fun openWhatsApp() {
        val uri = Uri.parse("smsto:$phone")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }

    private fun openLink(Link: String) {
        val uri = Uri.parse(Link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun send() {
        val TO = arrayOf(mail)
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail vs..."))
            Log.i("Finished email...", "Finished sending email...")
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show()
        }
    }

}