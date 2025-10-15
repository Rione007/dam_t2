package com.cibertec.dam_t2_villegas.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tb_reminders")
class Reminder {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var date_reminder: String = ""
    var id_user: Int = 0

    constructor(title: String, description: String, date_reminder: String, id_user: Int) {
        this.title = title
        this.description = description
        this.date_reminder = date_reminder
        this.id_user = id_user
    }
}