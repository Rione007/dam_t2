package com.cibertec.dam_t2_villegas.model.repository

import com.cibertec.dam_t2_villegas.model.Reminder
import com.cibertec.dam_t2_villegas.model.dao.ReminderDao


class ReminderRepository (private val dao : ReminderDao){

    suspend fun getAllReminder() = dao.getAllReminder()
    suspend fun getReminderByUserId(id:Int) = dao.getReminderByUserId(id)
    suspend fun  getReminderById(id:Int) = dao.getReminderById(id)
    suspend fun insert(reminder: Reminder) = dao.insert(reminder)
    suspend fun  update(reminder: Reminder) = dao.update(reminder)
    suspend fun  delete(reminder: Reminder) = dao.delete(reminder)
    


}