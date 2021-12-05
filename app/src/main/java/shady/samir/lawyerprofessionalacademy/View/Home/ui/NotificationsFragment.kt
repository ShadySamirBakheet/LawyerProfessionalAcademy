package shady.samir.lawyerprofessionalacademy.View.Home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shady.samir.lawyerprofessionalacademy.Apaters.Data.Notify.NotificationAdapter
import shady.samir.lawyerprofessionalacademy.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    lateinit var notifyList:RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        notifyList = binding.notifyList


        notifyList.setHasFixedSize(true)
        notifyList.layoutManager = LinearLayoutManager(context)
        notifyList.adapter = NotificationAdapter(context)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}