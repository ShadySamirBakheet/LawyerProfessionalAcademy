package shady.samir.lawyerprofessionalacademy.View.Programme

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityLiveLectureBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityPdfViewerBinding

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pdfView.fromAsset("test.pdf")
        //binding.pdfView.fromUri(Uri.parse("https://www.researchgate.net/profile/Matteo-Cortesi-2/publication/233972563_Power_production_of_the_lower_limbs_in_flutter-kick_swimming/links/0a85e539854c06971c000000/Power-production-of-the-lower-limbs-in-flutter-kick-swimming.pdf"))
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .enableAnnotationRendering(false)
            .password(null)
            .scrollHandle(null)
            .enableAntialiasing(true)
            .spacing(1)
            .load()

    }
}