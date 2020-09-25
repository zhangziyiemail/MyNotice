package com.example.github.mynotice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun notice(view: View){
        Intent().also {
            it.setAction("com.example.broadcast.MY_NOTIFICATION")
            it.putExtra("type", "notfied")
            it.putExtra("title", "Notice me senpai!")
            it.putExtra("data", "Notice me data!")
            sendBroadcast(it)
        }
    }
}