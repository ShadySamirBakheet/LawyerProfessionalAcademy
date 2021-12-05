package shady.samir.lawyerprofessionalacademy.View.App

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import shady.samir.lawyerprofessionalacademy.databinding.ActivityAboutAppBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityUserUpdateBinding

class AboutAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener {
            finish()
        }

        title = getString(intent.getIntExtra("title",0))

        val string = "\n" +
                "<div class=\"section-heading\">\n" +
                "            <h1>$title</h1>\n" +
                "</div>\n" +
                "<div class=\"row\" data-sf-element=\"Row\">\n" +
                "    <div id=\"PageContent_TA51A0481001_Col00\" class=\"sf_colsIn col-md-12\" data-sf-element=\"Column 1\" data-placeholder-label=\"Body content\"><div id=\"PageContent_C001_Col00\" class=\"sf_colsIn container\" data-sf-element=\"Container\" data-placeholder-label=\"Container\"><div class=\"row\" data-sf-element=\"Row\">\n" +
                "    <div id=\"PageContent_C003_Col00\" class=\"sf_colsIn col-md-3 empty\" data-sf-element=\"Column 1\" data-placeholder-label=\"Column 1\"></div>\n" +
                "    <div id=\"PageContent_C003_Col01\" class=\"sf_colsIn col-md-6\" data-sf-element=\"Column 2\" data-placeholder-label=\"Column 2\">\n" +
                "<div class=\"sf-content-block content-block\">\n" +
                "    <div><p>إنّ خصوصيتك على الإنترنت أمر في غاية الأهمية بالنسبة لمنظمة الصحة العالمية. ويشرح هذا البيان سياسة المنظمة فيما يتعلق بجمع وتقاسم المعلومات الشخصية التي يدلي بها الزوار على موقع المنظمة. وتلك السياسة تنطبق على \"جميع المواقع التابعة للمنظمة\"، أي جميع المواقع التي تدخل ضمن اسم المجال التالي: \"who.int\".</p><h2>ما هي المعلومات التي تجمعها المنظمة؟</h2><h3>الاستخدام العادي للموقع</h3><p>يمكنك، عموماً، تصفّح موقع المنظمة دون أن تكشف عن هويتك أو تدلي بأية معلومات شخصية. والمعلومات الوحيدة التي نجمعها أثناء التصفّح العام للموقع هي المعلومات العامة التي تُجمع في سجلات الخادم العادية. وتشمل تلك المعلومات عنوان بروتوكول الإنترنت(IP) واسم المجال الخاصين بك ونوع برنامج التصفّح ونظام التشغيل اللّذين تستخدمهما، فضلاً عن معلومات أخرى مثل عنوان الموقع الذي نفذت منه إلى موقعنا والملفات التي تحمّلها والصفحات التي تزورها وتواريخ/مواعيد تلك الزيارات.</p><h3>جمع المعلومات الشخصية التي يمكن الكشف عن صاحبها</h3><p>إذا أردت الاشتراك في رسالة إخبارية أو النفاذ إلى بعض من مواقع المنظمة أو طلب كتاب أو التماس معلومات أو الإدلاء بتعليقات أو طلب وظيفة أو المشاركة في مجموعة مناقشة أو الانضمام إلى قائمة بريدية إلكترونية، لا بد لك من تقديم معلومات شخصية، مثل اسمك وعنوانيك البريدي والإلكتروني. ولا يتم جمع تلك المعلومات إلا بعلمك وإذنك، ويتم الاحتفاظ بها في مختلف قواعد البيانات والقوائم البريدية التابعة للمنظمة. وقد يُطلب منك، إذا كنت تريد اقتناء شيء عن طريق الإنترنت، إعطاء تفاصيل بطاقتك الائتمانية. وتتم إحالة تلك التفاصيل إلى إحدى دوائر الدفع الإلكتروني المأمونة التي تضيّف مواقعها خارج نظام المنظمة. ولا تحتفظ المنظمة بالمعلومات الخاصة ببطاقات الائتمان.</p><p>ويجوز لمواقع المنظمة التي تضع شروطاً محدّدة لجمع المعلومات الشخصية أن تنشر سياسات خاصة بها تتعلق بالسرية. وفي تلك الحالات تكون السياسات الخاصة بتلك المواقع فيما يتعلق بالخصوصية مكمّلة للسياسة العامة التي تنتهجها المنظمة في هذا المجال، غير أنّها تتيح تفاصيل إضافية بخصوص تلك المواقع.</p><p>ويؤدي تسجيل معلومات شخصية أو التزويد بها، في بعض المواقع المعيّنة، إلى ظهور ملّف تتبّع المسار*. ويمكن للمنظمة، من خلال ذلك الملف، أن تتذكّر التفاصيل الخاصة بك لدى زيارتك الموقع مرّة أخرى، وبالتالي لن تضطر إلى تقديم المعلومات ذاتها ثانية. وذلك يساعدنا على تزويدك بخدمة أجود.</p><p>وقد يعني الانضمام إلى \"مجموعات مناقشة\" أنّه بإمكان مشاركين آخرين في المناقشة (بمن فيهم أناس آخرون يعملون في منظمات أخرى غير منظمة الصحة العالمية) الاطلاع على معلوماتك الشخصية التي قدّمتها بمحض إرادتك. وتلك المعلومات ستكون متاحة لجميع أعضاء مجموعات المناقشة المفتوحة.</p><h2>ماذا تفعل المنظمة بالمعلومات التي تجمعها؟</h2><h3>الاستخدام العادي للموقع</h3><p>تُستخدم المعلومات التي تُجمع أثناء تصفّح المجال \"who.i*"

        binding.privacy.text = Html.fromHtml(string)
        binding.toolbarTitle.text = title
    }
}