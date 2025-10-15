package com.cibertec.dam_t2_villegas.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cibertec.dam_t2_villegas.R
import com.cibertec.dam_t2_villegas.controller.UserController
import com.cibertec.dam_t2_villegas.model.User
import kotlin.text.clear

class RegistroActivity : AppCompatActivity() {
    private lateinit var userController : UserController

    private lateinit var txtName: EditText
    private lateinit var txtUsuario: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        userController = UserController(this)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val txtLogin = findViewById<Button>(R.id.txtLogin)

        txtName = findViewById<EditText>(R.id.txtName)
        txtUsuario = findViewById<EditText>(R.id.txtUsuario)
        txtPhone = findViewById<EditText>(R.id.txtPhone)
        txtPassword = findViewById<EditText>(R.id.txtPassword)


        btnRegistrar.setOnClickListener {
            val nametxt = txtName.text.toString()
            val usuariotxt = txtUsuario.text.toString()
            val phonetxt = txtPhone.text.toString()
            val passtxt = txtPassword.text.toString()
            val user = User(usuariotxt,passtxt,nametxt,phonetxt)
            userController.insertarUser(
                user,
                onInsert = {
                    Toast.makeText(this, "Usuario ${usuariotxt} registrado", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                },
                onError = {
                        exception -> Toast.makeText(this, "${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )

        }


        txtLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_registrar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun limpiarCampos() {
        txtName.text.clear()
        txtUsuario.text.clear()
        txtPhone.text.clear()
        txtPassword.text.clear()
    }

}