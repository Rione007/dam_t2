package com.cibertec.dam_t2_villegas.view.dialog

import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cibertec.dam_t2_villegas.R
import com.cibertec.dam_t2_villegas.controller.ReminderController
import com.cibertec.dam_t2_villegas.controller.UserController
import com.cibertec.dam_t2_villegas.model.Reminder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddReminderDialog(
    private val context: Context,
    private val id:Int,
    private val onSaved: () -> Unit) {

    private lateinit var reminderController : ReminderController

    fun show() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_add_reminder)

        val txtTitle = dialog.findViewById<EditText>(R.id.txtTitulo)
        val txtDescription = dialog.findViewById<EditText>(R.id.txtDescription)
        val btnSave = dialog.findViewById<Button>(R.id.btnSave)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)

        reminderController = ReminderController(context)

        btnSave.setOnClickListener {
            val title = txtTitle.text.toString()
            val descripcion = txtDescription.text.toString()

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val fechaActual = sdf.format(Date())

            val reminder = Reminder(
                title,
                descripcion,
                fechaActual,
                id
            )

            reminderController.insert(
                reminder,
                onInserted = {
                    Toast.makeText(context, "Recordatorio guardado", Toast.LENGTH_SHORT).show()
                    onSaved()
                },
                onError = {
                        exception -> Toast.makeText(context, "${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )

            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(
            (context.resources.displayMetrics.widthPixels * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }


}