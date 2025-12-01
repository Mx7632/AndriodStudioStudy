package com.example.tiktok

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class Activity2 : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val tvMain = findViewById<TextView>(R.id.tv_main)
        tvMain.setOnClickListener {
            finish()
        }
    }
}
