package com.cibertec.dam_t2_villegas.view.activities

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.dam_t2_villegas.R
import com.cibertec.dam_t2_villegas.controller.ReminderController
import com.cibertec.dam_t2_villegas.controller.UserController
import com.cibertec.dam_t2_villegas.model.Reminder
import com.cibertec.dam_t2_villegas.view.adapter.ReminderAdapter
import com.cibertec.dam_t2_villegas.view.dialog.AddReminderDialog
import com.cibertec.dam_t2_villegas.view.fragment.ReminderDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReminderActivity : AppCompatActivity() {

    private lateinit var adapter : ReminderAdapter
    private lateinit var floatbutton : FloatingActionButton
    private lateinit var reminderController : ReminderController

    private lateinit var frameDetail : FrameLayout
    private lateinit var  rvReminders : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reminder)

        reminderController = ReminderController(this)

        floatbutton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        rvReminders = findViewById<RecyclerView>(R.id.rvReminders)

        frameDetail = findViewById<FrameLayout>(R.id.frameDetail)

        val fullnametitle = findViewById<TextView>(R.id.fullnametitle)

        val prefs = getSharedPreferences("user_session", MODE_PRIVATE)
        val userid = prefs.getInt("id", -1)
        val fullname = prefs.getString("fullname", "")

        fullnametitle.text = fullname

        loadReminders()
        floatbutton.setOnClickListener {
            AddReminderDialog(this,userid,{
                loadReminders()
            }).show()
        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun loadReminders(){
        val prefs = getSharedPreferences("user_session", MODE_PRIVATE)
        val userid = prefs.getInt("id", -1)
        reminderController.getAllReminder(
            onStart ={

            },
            onSuccess = { lista ->
                adapter = ReminderAdapter(
                    lista.filter { it.id_user == userid },
                    onDeleteClick = {
                        Toast.makeText(this, "funcion no disponible :)", Toast.LENGTH_SHORT).show()
                    },
                    onEditClick = {
                        Toast.makeText(this, "funcion no disponible jeje :v", Toast.LENGTH_SHORT).show()
                    },
                    onItemClick = {
                        reminder ->
                        val fragment = ReminderDetailFragment.newInstance(reminder)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameDetail, fragment)
                            .addToBackStack(null)
                            .commit()

                        frameDetail.visibility = android.view.View.VISIBLE
                        rvReminders.visibility = android.view.View.GONE
                    }
                )
                rvReminders.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
                rvReminders.adapter = adapter
            },
            onError = {
                    exception -> Toast.makeText(this, "${exception.message}", Toast.LENGTH_SHORT).show()

            }
        )
    }
}