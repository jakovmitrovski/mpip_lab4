package com.example.a186010_lab4_mpip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email: EditText = this.findViewById(R.id.etUsername)
        val password: EditText = this.findViewById(R.id.etPassword)

        val loginButton: Button = this.findViewById(R.id.btnLogin)
        val registerButton: Button = this.findViewById(R.id.btnRegister)

        loginButton.setOnClickListener {
            val emailValue = email.text.toString()
            val passwordValue = password.text.toString()

            if(emailValue.isEmpty() || passwordValue.isEmpty()) {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }else {
                login(emailValue, passwordValue)
            }


        }

        registerButton.setOnClickListener {
            val emailValue = email.text.toString()
            val passwordValue = password.text.toString()

            if(emailValue.isEmpty() || passwordValue.isEmpty()) {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }else {
                register(emailValue, passwordValue)
            }
        }
    }

    private fun register(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this, "Registration successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this, "Registration failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    navigateOut()
                } else {
                    Toast.makeText(
                        this, "Login failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

        if (isLoggedIn()){
            navigateOut()
        }
    }

    private fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }

    private fun navigateOut() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}