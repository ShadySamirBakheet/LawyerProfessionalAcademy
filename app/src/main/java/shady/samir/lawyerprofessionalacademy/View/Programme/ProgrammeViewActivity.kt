package shady.samir.lawyerprofessionalacademy.View.Programme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityNotificationViewBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityProgrammeViewBinding

class ProgrammeViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgrammeViewBinding

    val value = "<div class=\"tab-pane active\" id=\"tab-1\">\n" +
            "    <h3 class=\"card-title mb-3 font-weight-bold\">\n" +
            "        <font style=\"vertical-align: inherit;\">\n" +
            "            <font style=\"vertical-align: inherit;\">ماذا سأتعلم؟</font>\n" +
            "        </font>\n" +
            "    </h3>\n" +
            "    <ul class=\"list-group mb-5\">\n" +
            "\n" +
            "        <li class=\"mb-2\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i>\n" +
            "            <font style=\"vertical-align: inherit;\">\n" +
            "                <font style=\"vertical-align: inherit;\"> من الممكن أن يكون الوضع مؤقتًا في حالة ممارسة العمل والخرق عند\n" +
            "                    ممارسة العمل بشكل سلبي. </font>\n" +
            "            </font>\n" +
            "        </li>\n" +
            "        <li class=\"mb-2\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i>\n" +
            "            <font style=\"vertical-align: inherit;\">\n" +
            "                <font style=\"vertical-align: inherit;\">كل ما في الأمر هو الحد الأدنى من التمرين ، ممارسة العمل على\n" +
            "                </font>\n" +
            "            </font>\n" +
            "        </li>\n" +
            "    </ul>\n" +
            "    <h3 class=\"card-title mb-3 font-weight-bold\">\n" +
            "        <font style=\"vertical-align: inherit;\">\n" +
            "            <font style=\"vertical-align: inherit;\">وصف</font>\n" +
            "        </font>\n" +
            "    </h3>\n" +
            "    <div class=\"mb-0\">\n" +
            "        <p>\n" +
            "            <font style=\"vertical-align: inherit;\">\n" +
            "                <font style=\"vertical-align: inherit;\">من ناحية أخرى ، نشجب بسخط صالح ونكره الرجال الذين تم خداعهم\n" +
            "                    وإحباطهم من سحر متعة اللحظة ، الذين أعمتهم الرغبة ، لدرجة أنهم لا يستطيعون التنبؤ بالألم والمتاعب\n" +
            "                    التي ستنجم عن ذلك ؛ </font>\n" +
            "                <font style=\"vertical-align: inherit;\">واللوم المتساوي يقع على من يفشل في أداء واجبه بسبب ضعف الإرادة ،\n" +
            "                    وهو نفس القول بالانكماش من الكد والألم.</font>\n" +
            "            </font>\n" +
            "        </p>\n" +
            "    </div>\n" +
            "    <h4 class=\"card-title mb-3 font-weight-bold\">\n" +
            "        <font style=\"vertical-align: inherit;\">\n" +
            "            <font style=\"vertical-align: inherit;\">تحديد</font>\n" +
            "        </font>\n" +
            "    </h4>\n" +
            "    <div class=\"row\">\n" +
            "        <div class=\"col-xl-6 col-md-12\">\n" +
            "            <ul class=\"list-unstyled widget-spec mb-0\">\n" +
            "                <li class=\"\"> <i class=\"fa fa-star text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\">عرض تجريبي مجاني </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"\"> <i class=\"fa fa-user text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> 100٪ مساعدة وظيفية </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"\"> <i class=\"fa fa-times-circle-o  text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> توقيت مرن </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"\"> <i class=\"fa fa-file-word-o  text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> عمل المشروع في الوقت الحقيقي </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"\"> <i class=\"fa fa-users text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> تعلم من الخبراء </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"mb-xl-0\"> <i class=\"fa fa-certificate  text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> الحصول على شهادة </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "        <div class=\"col-xl-6 col-md-12\">\n" +
            "            <ul class=\"list-unstyled widget-spec mb-0\">\n" +
            "                <li class=\"\"> <i class=\"fa fa-child\tild text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> ضع حياتك المهنية </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"\"> <i class=\"fa fa-building-o text-muted w-5\"></i>\n" +
            "                    <font style=\"vertical-align: inherit;\">\n" +
            "                        <font style=\"vertical-align: inherit;\"> رسوم معقولة </font>\n" +
            "                    </font>\n" +
            "                </li>\n" +
            "                <li class=\"\"> <i class=\"fa fa-laptop text-muted w-5\"></i>\n" + "                    <font style=\"vertical-align: inherit;\">\n" + "                        <font style=\"vertical-align: inherit;\"> الوصول على الهاتف المحمول والتلفزيون </font>\n" + "                    </font>\n" + "                </li>\n" + "                <li class=\"\"> <i class=\"fa fa-star text-muted w-5\"></i>\n" + "                    <font style=\"vertical-align: inherit;\">\n" + "                        <font style=\"vertical-align: inherit;\"> محتوى عالي الجودة ومقاطع فيديو صفية </font>\n" + "                    </font>\n" + "                </li>\n" + "                <li class=\"\"> <i class=\"fa fa-futbol-o text-muted w-5\"></i>\n" + "                    <font style=\"vertical-align: inherit;\">\n" + "                        <font style=\"vertical-align: inherit;\"> نظام إدارة التعلم </font>\n" + "                    </font>\n" + "                </li>\n" + "                <li class=\"mb-0\"> <i class=\"fa fa-id-badge  text-muted w-5\"></i>\n" + "                    <font style=\"vertical-align: inherit;\">\n" + "                        <font style=\"vertical-align: inherit;\"> وصول كامل مدى الحياة </font>\n" + "                    </font>\n" + "                </li>\n" + "            </ul>\n" + "        </div>\n" + "    </div>\n" + "</div>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProgrammeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.descItem.text = Html.fromHtml(value)

        binding.goBack.setOnClickListener {
            finish()
        }
    }
}