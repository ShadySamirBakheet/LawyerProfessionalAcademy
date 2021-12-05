package shady.samir.lawyerprofessionalacademy.View.Home.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Programes.LectureAdapter
import shady.samir.lawyerprofessionalacademy.View.Programme.LiveLectureActivity
import shady.samir.lawyerprofessionalacademy.databinding.FragmentCoursesBinding

class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var isEnd = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.listLecture.apply{
            setHasFixedSize(true)
            //layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            layoutManager = LinearLayoutManager(context)
            adapter = LectureAdapter(context, isEnd, true)
        }

        binding.nextLec.setOnClickListener {
            startActivity(Intent(context, LiveLectureActivity::class.java))
        }

        binding.status.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //TODO("Not yet implemented")
                if (tab != null) {
                    when(tab.position){
                        0->{
                            isEnd = true
                         //   Toast.makeText(context, "${tab.position} is end $isEnd", Toast.LENGTH_SHORT).show()
                            binding.listLecture.apply{
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = LectureAdapter(context, isEnd, true)
                            }
                        }
                        1->{
                            isEnd = false
                           // Toast.makeText(context, "${tab.position} is end $isEnd", Toast.LENGTH_SHORT).show()
                            binding.listLecture.apply{
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = LectureAdapter(context, isEnd, true)
                            }
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //TODO("Not yet implemented")
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}