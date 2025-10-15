package com.cibertec.dam_t2_villegas.controller

import android.content.Context
import androidx.room.Insert
import com.cibertec.dam_t2_villegas.model.User
import com.cibertec.dam_t2_villegas.model.Validar
import com.cibertec.dam_t2_villegas.model.db.AppDatabase
import com.cibertec.dam_t2_villegas.model.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.text.insert

class UserController(context: Context) {

    private val repository : UserRepository

    init {
        val db = AppDatabase.getDatabase(context)
        repository = UserRepository(db.userDao())
    }

    fun validarUser(
        user: Validar,
        onSuccess: (User) -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = repository.validarUser(user.user,user.passwod)
                withContext(Dispatchers.Main) {
                    if (user != null) {
                        onSuccess(user)
                    } else {
                        onError(Exception("Credenciales incorrectas"))
                    }
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun insertarUser(
        user: User,
        onInsert: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.insert(user)
                withContext(Dispatchers.Main) {
                    if (user != null) {
                        onInsert()
                    } else {
                        onError(Exception("Error al registrarse"))
                    }
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}