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
import com.cibertec.dam_t2_villegas.model.Validar


class MainActivity : AppCompatActivity() {

    private lateinit var userController : UserController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userController = UserController(this)


        val txtUser = findViewById<EditText>(R.id.txtUser)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtRegistro = findViewById<TextView>(R.id.txtRegistro)

        btnLogin.setOnClickListener {
            val usertxt = txtUser.text.toString()
            val password = txtPassword.text.toString()
            val valid = Validar(usertxt, password)
            userController.validarUser(
                valid,
                onSuccess = {
                    User ->
                    Toast.makeText(this, "Bienvenido ${User.fullname}", Toast.LENGTH_SHORT).show()
                    val prefs = getSharedPreferences("user_session", MODE_PRIVATE)
                    prefs.edit().apply {
                        putInt("id", User.id)
                        putString("fullname", User.fullname)
                        apply()
                    }

                    val intent = Intent(this, ReminderActivity::class.java)
                    startActivity(intent)
                    finish()
                }
              ,
                onError = {
                    exception -> Toast.makeText(this, "${exception.message}", Toast.LENGTH_SHORT).show()
                })

        }

        txtRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}