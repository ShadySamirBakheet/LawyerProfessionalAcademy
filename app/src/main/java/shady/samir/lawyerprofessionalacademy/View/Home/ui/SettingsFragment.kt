package shady.samir.lawyerprofessionalacademy.View.Home.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplexgold.aplexshipping.Adapters.Data.Home.ExpandableListAdapter
import com.aplexgold.aplexshipping.Adapters.Data.Home.MenuAdapter
import com.aplexgold.aplexshipping.Data.Model.Home.MenuItem
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.View.App.AboutAppActivity
import shady.samir.lawyerprofessionalacademy.View.App.AppSettingsActivity
import shady.samir.lawyerprofessionalacademy.View.App.ContactUsActivity
import shady.samir.lawyerprofessionalacademy.View.LoginSystem.SecuritySettingActivity
import shady.samir.lawyerprofessionalacademy.databinding.FragmentSettingsBinding
import shady.samir.lawyerprofessionalacademy.View.LoginSystem.UserUpdateActivity
import shady.samir.lawyerprofessionalacademy.View.Programme.MyProgrammeActivity

class SettingsFragment : Fragment() {

    lateinit var advCon:RelativeLayout
    lateinit var setCon:RelativeLayout
    lateinit var appCon2:RelativeLayout
    lateinit var appCon:LinearLayout
    lateinit var settingCon:LinearLayout
    lateinit var conMain:LinearLayout
    lateinit var menuRView:RecyclerView
    lateinit var advCll:ImageView
    lateinit var setCll:ImageView
    lateinit var appCll:ImageView
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var listAdapter: ExpandableListAdapter
    private lateinit var expListView: ExpandableListView
    private lateinit var listDataHeader: ArrayList<MenuItem>
    private lateinit var listDataChild: HashMap<MenuItem, List<MenuItem>>

    private var lastPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        advCon = binding.advCon
        expListView = binding.menuListView
        menuRView = binding.menuRView
        advCll = binding.advCll
        setCll = binding.setCll
        appCll = binding.appCll
        appCon = binding.appCon
        settingCon = binding.settingCon
        setCon = binding.setCon
        appCon2 = binding.appCon2
        conMain = binding.conMain

        setDefaultsFun(0)
        setAnimation()

        advCon.setOnClickListener {
            setDefaultsFun(1)
           if ( menuRView.visibility == GONE){
               menuRView.visibility = VISIBLE
               advCll.setImageResource(R.drawable.ic_up_exg)
           }else{
               menuRView.visibility = GONE
               advCll.setImageResource(R.drawable.ic_down_exg)
           }
        }

        setCon.setOnClickListener {
            setDefaultsFun(2)
           if ( settingCon.visibility == GONE){
               settingCon.visibility = VISIBLE
               setCll.setImageResource(R.drawable.ic_up_exg)
           }else{
               settingCon.visibility = GONE
               setCll.setImageResource(R.drawable.ic_down_exg)
           }
        }

        appCon2.setOnClickListener {
            setDefaultsFun(3)
           if ( appCon.visibility == GONE){
               appCon.visibility = VISIBLE
               appCll.setImageResource(R.drawable.ic_up_exg)
           }else{
               appCon.visibility = GONE
               appCll.setImageResource(R.drawable.ic_down_exg)
           }
        }

        ///viewMuenList()
        viewMuenList2()

        binding.accountSettings.setOnClickListener {
            startActivity(Intent(context, UserUpdateActivity::class.java))
        }

        binding.profileCon.setOnClickListener {
            startActivity(Intent(context, UserUpdateActivity::class.java))
        }

        binding.aboutApp.setOnClickListener {
            startActivity(Intent(context, AboutAppActivity::class.java).putExtra("title",R.string.about_application))
        }

        binding.usage.setOnClickListener {
            startActivity(Intent(context, AboutAppActivity::class.java).putExtra("title",R.string.usage_policies))
        }

        binding.privacy.setOnClickListener {
            startActivity(Intent(context, AboutAppActivity::class.java).putExtra("title",R.string.privacy_policy))
        }

        binding.contacts.setOnClickListener {
            startActivity(Intent(context, ContactUsActivity::class.java))
        }

        binding.appSetting.setOnClickListener {
            startActivity(Intent(context, AppSettingsActivity::class.java).putExtra("appSetting",true))
        }

        binding.securitySetting.setOnClickListener {
            startActivity(Intent(context, SecuritySettingActivity::class.java))
        }

        binding.myProgramme.setOnClickListener {
            startActivity(Intent(context, MyProgrammeActivity::class.java))
        }

        binding.shareApp.setOnClickListener {
            shareAppFun()
        }

        binding.notification.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_settings_to_navigation_notifications
            )
        }

        return root
    }


    private fun shareAppFun() {
        val msg = "${context?.getString(R.string.app_name)}\n\n${context?.getString(R.string.shareGoogleMSG)}:\n https://play.google.com/store/apps/details?id=" + context?.packageName
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, msg)
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun setDefaultsFun(item: Int) {
        if (item == 1){
            appCon.visibility = GONE
            appCll.setImageResource(R.drawable.ic_down_exg)
            settingCon.visibility = GONE
            setCll.setImageResource(R.drawable.ic_down_exg)
        }else if( item == 2){
            appCon.visibility = GONE
            appCll.setImageResource(R.drawable.ic_down_exg)
            menuRView.visibility = GONE
            advCll.setImageResource(R.drawable.ic_down_exg)
        }else if( item == 3){
            settingCon.visibility = GONE
            setCll.setImageResource(R.drawable.ic_down_exg)
            menuRView.visibility = GONE
            advCll.setImageResource(R.drawable.ic_down_exg)
        }else{
            appCon.visibility = GONE
            appCll.setImageResource(R.drawable.ic_down_exg)
            settingCon.visibility = GONE
            setCll.setImageResource(R.drawable.ic_down_exg)
            menuRView.visibility = GONE
            advCll.setImageResource(R.drawable.ic_down_exg)
        }
    }

    private fun setAnimation() {
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.in_right)
        animation.duration = 2000
        conMain.startAnimation(animation)
    }

    private fun viewMuenList2() {

        val item1: ArrayList<MenuItem> = ArrayList()
        ///item1.add(MenuItem(getString(R.string.child11), R.drawable.ic_menu, false, 1))
        item1.add(MenuItem("برنامج الأول", R.drawable.learning, false, 1, null))
        item1.add(MenuItem("برنامج الثاني", R.drawable.learning, false, 2, null))
        item1.add(MenuItem("برنامج الثالث", R.drawable.learning, false, 3, null))
        item1.add(MenuItem("برنامج الرابع", R.drawable.learning, false, 4, null))
        item1.add(MenuItem("برنامج الخامس", R.drawable.learning, false, 5, null))

        listDataHeader = ArrayList()
        listDataHeader.add(MenuItem("برنامج المحامي القانوني", R.drawable.onlinecourse, true, 1, item1))
        listDataHeader.add(MenuItem("برنامج المحامي المحاسب", R.drawable.onlinecourse, true, 2, item1))
        listDataHeader.add(MenuItem("برنامج المحامي الشركات", R.drawable.onlinecourse, true, 2, item1))
        listDataHeader.add(MenuItem("برنامج المحامي الجنائي", R.drawable.onlinecourse, true, 2, item1))
        listDataHeader.add(MenuItem("برنامج المحامي المدني", R.drawable.onlinecourse, true, 2, item1))

        menuRView.setHasFixedSize(true)
        menuRView.layoutManager = LinearLayoutManager(context)
        menuRView.adapter = MenuAdapter(context,listDataHeader)
    }


    private fun viewMuenList() {
        prepareListData()

        listAdapter = context?.let { ExpandableListAdapter(it, listDataHeader, listDataChild) }!!
        expListView.setAdapter(listAdapter)

        expListView.setOnGroupExpandListener { groupPosition ->
            if (lastPosition != -1 && groupPosition != lastPosition) {
                expListView.collapseGroup(lastPosition)
            }
            lastPosition = groupPosition
        }

        expListView.setOnGroupCollapseListener { groupPosition ->

        }

        expListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id -> // TODO Auto-generated method stub

            when(groupPosition){
                0->{
                    when(childPosition){
                        ///0-> startActivityFun(Intent(context, AddCustomerActivity::class.java),false)
                      //  0-> startActivityFun(Intent(context, ViewCustomersActivity::class.java),false)
                    }

                }

            }

            false
        }
    }

    private fun startActivityFun(intent: Intent, isFinish: Boolean) {
        startActivity(intent)
        if (isFinish){
            activity?.finish()
        }
    }

    private fun prepareListData() {
        listDataHeader = ArrayList()
        listDataChild = HashMap<MenuItem, List<MenuItem>>()

        listDataHeader.add(MenuItem("item", R.drawable.ic_courses, true, 1, null))
        listDataHeader.add(MenuItem("item", R.drawable.ic_courses, true, 2, null))

        val item1: MutableList<MenuItem> = ArrayList()
        ///item1.add(MenuItem(getString(R.string.child11), R.drawable.ic_menu, false, 1))
        item1.add(MenuItem("item", R.drawable.ic_courses, false, 1, null))
        item1.add(MenuItem("item", R.drawable.ic_courses, false, 2, null))
        item1.add(MenuItem("item", R.drawable.ic_courses, false, 3, null))
        item1.add(MenuItem("item", R.drawable.ic_courses, false, 4, null))
        item1.add(MenuItem("item", R.drawable.ic_courses, false, 5, null))

        val item0: MutableList<MenuItem> = ArrayList()

        listDataChild.put(listDataHeader.get(0), item1)
        listDataChild.put(listDataHeader.get(1), item1)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}