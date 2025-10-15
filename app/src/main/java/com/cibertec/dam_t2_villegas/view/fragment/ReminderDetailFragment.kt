package com.cibertec.dam_t2_villegas.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.dam_t2_villegas.R
import com.cibertec.dam_t2_villegas.model.Reminder


class ReminderDetailFragment : Fragment() {

    private var reminder: Reminder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reminder_detail, container, false)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitleDetail)
        val txtDesc = view.findViewById<TextView>(R.id.txtDescDetail)
        val txtDate = view.findViewById<TextView>(R.id.txtDateDetail)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val dateReminder = arguments?.getString("dateReminder")

        txtTitle.text = title
        txtDesc.text = description
        txtDate.text = dateReminder

        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this)
                .commit()
            requireActivity().findViewById<RecyclerView>(R.id.rvReminders).visibility = View.VISIBLE
            requireActivity().findViewById<FrameLayout>(R.id.frameDetail).visibility = View.GONE
        }


        return view
    }

    companion object {
        fun newInstance(reminder: Reminder): ReminderDetailFragment {
            val fragment = ReminderDetailFragment()
            val bundle = Bundle()
            bundle.putInt("id", reminder.id)
            bundle.putString("title", reminder.title)
            bundle.putString("description", reminder.description)
            bundle.putString("dateReminder", reminder.dateReminder)
            fragment.arguments = bundle
            return fragment
        }
    }
}