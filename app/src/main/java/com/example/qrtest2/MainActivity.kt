package com.example.qrtest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScanEan = findViewById<Button>(R.id.btn_scan_ean)
        btnScanEan.setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }
}