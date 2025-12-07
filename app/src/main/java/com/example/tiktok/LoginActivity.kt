package com.example.tiktok

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tiktok.db.UserDatabaseHelper

class LoginActivity : Activity() {

    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = UserDatabaseHelper(this)

        val etUsername = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnWechat = findViewById<Button>(R.id.btn_wechat)
        val btnApple = findViewById<Button>(R.id.btn_apple)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (verifyLogin(username, password)) {
                // Login Success
                saveUserInfoToPreferences(username)
                startActivity(Intent(this, PersonalCenterActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        btnWechat.setOnClickListener {
            Toast.makeText(this, "WeChat Login Clicked", Toast.LENGTH_SHORT).show()
        }

        btnApple.setOnClickListener {
            Toast.makeText(this, "Apple Login Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyLogin(user: String, pass: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            UserDatabaseHelper.TABLE_NAME,
            arrayOf(UserDatabaseHelper.COLUMN_ID),
            "${UserDatabaseHelper.COLUMN_USERNAME}=? AND ${UserDatabaseHelper.COLUMN_PASSWORD}=?",
            arrayOf(user, pass),
            null, null, null
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    private fun saveUserInfoToPreferences(username: String) {
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("username", username)
            // Save a default signature if not exists
            if (!sharedPref.contains("signature")) {
                putString("signature", "This is a default signature.")
            }
            apply()
        }
    }
}
