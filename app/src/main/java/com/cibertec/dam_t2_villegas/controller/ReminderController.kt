package com.cibertec.dam_t2_villegas.controller

import android.content.Context
import com.cibertec.dam_t2_villegas.model.Reminder
import com.cibertec.dam_t2_villegas.model.User
import com.cibertec.dam_t2_villegas.model.Validar
import com.cibertec.dam_t2_villegas.model.db.AppDatabase
import com.cibertec.dam_t2_villegas.model.repository.ReminderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReminderController(context: Context) {
    private val repository : ReminderRepository

    init {
        val db = AppDatabase.getDatabase(context)
        repository = ReminderRepository(db.reminderDao())
    }

    fun insert(
        reminder: Reminder,
        onInserted: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.insert(reminder)
                withContext(Dispatchers.Main) {
                    onInserted()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }


    fun getAllReminder(
        onStart: () -> Unit = {},
        onSuccess: (List<Reminder>) -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val lista = repository.getAllReminder()
                withContext(Dispatchers.Main) {
                    onSuccess(lista)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }

}