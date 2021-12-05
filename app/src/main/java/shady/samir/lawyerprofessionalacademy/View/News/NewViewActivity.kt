package shady.samir.lawyerprofessionalacademy.View.News

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.lawyerprofessionalacademy.Apaters.Data.News.NewsHomeAdapter
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityNewViewBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityProgrammeViewBinding

class NewViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewViewBinding

    val value =" <h2 class=\"font-weight-semibold\">\n" +
            "            <font style=\"vertical-align: inherit;\">\n" +
            "                <font style=\"vertical-align: inherit;\">ادارة اعمال</font>\n" +
            "            </font>\n" +
            "        </h2>" +
            "<div class=\"card-body\">\n" +
            "    <p>\n" +
            "        <font style=\"vertical-align: inherit;\">\n" +
            "            <font style=\"vertical-align: inherit;\">لكن يجب أن أشرح لك كيف ولدت كل هذه الفكرة الخاطئة المتمثلة في إدانة\n" +
            "                المتعة ومدح الألم ، وسأقدم لك وصفًا كاملاً للنظام ، وأشرح التعاليم الفعلية للمستكشف العظيم للحقيقة ،\n" +
            "                الباني البارع للإنسان سعادة. </font>\n" +
            "            <font style=\"vertical-align: inherit;\">لا أحد يرفض أو يكره أو يتجنب المتعة نفسها ، لأنها متعة ، ولكن لأن\n" +
            "                أولئك الذين لا يعرفون كيفية السعي وراء المتعة يواجهون عواقب مؤلمة للغاية.</font>\n" +
            "        </font>\n" +
            "    </p>\n" +
            "</div>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.descItem.text = Html.fromHtml(value)

        binding.goBack.setOnClickListener {
            finish()
        }

        binding.newsList.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            //layoutManager = LinearLayoutManager(context)
            adapter = NewsHomeAdapter(context)
        }

    }
}