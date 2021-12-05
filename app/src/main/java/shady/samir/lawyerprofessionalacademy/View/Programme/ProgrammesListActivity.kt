package shady.samir.lawyerprofessionalacademy.View.Programme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.lawyerprofessionalacademy.Apaters.Data.News.NewsHomeAdapter
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Programes.ProgramesAdapter
import shady.samir.lawyerprofessionalacademy.R
import shady.samir.lawyerprofessionalacademy.databinding.ActivityNotificationViewBinding
import shady.samir.lawyerprofessionalacademy.databinding.ActivityProgrammesListBinding

class ProgrammesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgrammesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammesListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.programmeList.apply{
            setHasFixedSize(true)
            //layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            layoutManager = LinearLayoutManager(context)
            adapter = ProgramesAdapter(context)
        }

        binding.goBack.setOnClickListener {
            finish()
        }
    }
}