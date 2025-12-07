package com.example.tiktok

import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tiktok.ui.theme.MyStudyApplicationTheme

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvMain = findViewById<TextView>(R.id.tv_main)
        tvMain.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
        }


    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyStudyApplicationTheme {
        Greeting("Android")
    }
}