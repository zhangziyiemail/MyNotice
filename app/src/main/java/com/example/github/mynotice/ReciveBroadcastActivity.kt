package com.example.github.mynotice

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle

/**
 *   Created by zhangziyi on 9/24/20 19:17
 */
class ReciveBroadcastActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle(intent.getStringExtra("title"))
            .setMessage(intent.getStringExtra("detail"))
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: AlertDialog = builder.create()
        alert.show()

    }
}