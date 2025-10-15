package com.cibertec.dam_t2_villegas.model.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cibertec.dam_t2_villegas.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM tb_user ")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM tb_user WHERE id  = :id")
    suspend fun getUserById(id: Int): User

    @Query("DELETE FROM tb_user")
    suspend fun clearUsers()

    @Insert
    suspend fun insert(user: User)

    @Insert
    fun insertAll(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM tb_user u WHERE u.user = :user AND u.password = :password")
    suspend fun validarUser(user: String,password: String): User
}