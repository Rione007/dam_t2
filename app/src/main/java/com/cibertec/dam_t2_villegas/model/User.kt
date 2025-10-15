package com.cibertec.dam_t2_villegas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user")
class User {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var user: String = ""
    var password: String = ""
    var fullname: String = ""
    var phone: String = ""

    constructor(user: String, password: String, fullname: String, phone: String) {
        this.user = user
        this.password = password
        this.fullname = fullname
        this.phone = phone
    }

    companion object {
        fun getUsuerios(): List<User>{
            return listOf(
                User("rione","1234","Anderson Villegas","951163193")
            )
        }
    }
}