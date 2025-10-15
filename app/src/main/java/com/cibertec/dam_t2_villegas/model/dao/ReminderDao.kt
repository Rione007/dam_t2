package com.cibertec.dam_t2_villegas.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cibertec.dam_t2_villegas.model.Reminder


@Dao
interface ReminderDao {

    @Query("SELECT * FROM tb_reminders")
    suspend fun getAllReminder(): List<Reminder>

    @Query("SELECT * FROM tb_reminders r JOIN tb_user u ON r.id_user = u.id WHERE u.id = :id")
    suspend fun getReminderByUserId(id: Int): Reminder

    @Query("SELECT * FROM tb_reminders WHERE id  = :id")
    suspend fun getReminderById(id: Int): Reminder

    @Insert
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)
}