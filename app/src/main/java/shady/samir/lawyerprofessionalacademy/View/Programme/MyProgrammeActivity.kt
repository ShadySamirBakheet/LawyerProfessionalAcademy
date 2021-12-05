package shady.samir.lawyerprofessionalacademy.View.Programme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Programes.DocumentAdapter
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Programes.LectureAdapter
import shady.samir.lawyerprofessionalacademy.databinding.ActivityMyProgrammeBinding

class MyProgrammeActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyProgrammeBinding

    var isEnd = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyProgrammeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listLecture.apply{
            setHasFixedSize(true)
            //layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            layoutManager = LinearLayoutManager(this@MyProgrammeActivity)
            adapter = LectureAdapter(this@MyProgrammeActivity, isEnd, false)
        }

        binding.status.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //TODO("Not yet implemented")
                if (tab != null) {
                    when(tab.position){
                        0->{
                            isEnd = true
                            //Toast.makeText(this@MyProgrammeActivity, "${tab.position} is end $isEnd", Toast.LENGTH_SHORT).show()
                            binding.listLecture.apply{
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = LectureAdapter(context, isEnd, false)
                            }
                        }
                        1->{
                            isEnd = false
                           // Toast.makeText(this@MyProgrammeActivity, "${tab.position} is end $isEnd", Toast.LENGTH_SHORT).show()
                            binding.listLecture.apply{
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = LectureAdapter(context, isEnd, false)
                            }
                        }
                        2->{
                            isEnd = false
                         //   Toast.makeText(this@MyProgrammeActivity, "${tab.position} is end $isEnd", Toast.LENGTH_SHORT).show()
                            binding.listLecture.apply{
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = DocumentAdapter(context)
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

        binding.goBack.setOnClickListener {
            finish()
        }

    }
}