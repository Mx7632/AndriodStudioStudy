package com.example.tiktok

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class PersonalCenterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_center)

        val tvUsername = findViewById<TextView>(R.id.tv_username)
        val tvSignature = findViewById<TextView>(R.id.tv_signature)

        // Read from SharedPreferences
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest")
        val signature = sharedPref.getString("signature", "No signature")

        tvUsername.text = username
        tvSignature.text = signature

        // Set click listeners for items
        setupItemClick(R.id.item_personal_info, "Personal Info Clicked")
        setupItemClick(R.id.item_favorites, "My Favorites Clicked")
        setupItemClick(R.id.item_history, "Browsing History Clicked")
        setupItemClick(R.id.item_settings, "Settings Clicked")
        setupItemClick(R.id.item_about, "About Us Clicked")
        setupItemClick(R.id.item_feedback, "Feedback Clicked")
    }

    private fun setupItemClick(viewId: Int, message: String) {
        findViewById<View>(viewId).setOnClickListener {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
