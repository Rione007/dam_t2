package com.cibertec.dam_t2_villegas.model.repository

import com.cibertec.dam_t2_villegas.model.User
import com.cibertec.dam_t2_villegas.model.dao.UserDao

class UserRepository (private val dao : UserDao){
    suspend fun getAllUser() = dao.getAllUsers()
    suspend fun  getUserById(id:Int) = dao.getUserById(id)
    suspend fun clearUsers() = dao.clearUsers()
    suspend fun  insert(user : User) = dao.insert(user)
    suspend fun update(user : User) = dao.update(user)
    suspend fun delete(user : User) = dao.delete(user)

    suspend fun  validarUser (user: String,password: String) =dao.validarUser(user,password)
}