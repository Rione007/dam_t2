package com.cibertec.dam_t2_villegas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_reminders")
class Reminder {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var dateReminder: String = ""
    var id_user: Int = 0

    constructor(title: String, description: String, dateReminder: String, id_user: Int) {
        this.title = title
        this.description = description
        this.dateReminder = dateReminder
        this.id_user = id_user
    }
}