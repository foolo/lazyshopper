package com.example.qrtest2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScanEan = findViewById<Button>(R.id.btn_scan_ean)
        btnScanEan.setOnClickListener { scanCode() }
    }

    fun scanCode() {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_EAN_13)
            .build()
        val scanner = GmsBarcodeScanning.getClient(this, options)
        val barcodeValue = findViewById<TextView>(R.id.barcode_value)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                val rawValue: String? = barcode.rawValue
                barcodeValue.text = rawValue
                if(barcode.valueType == Barcode.TYPE_URL) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rawValue))
                    startActivity(browserIntent)
                }
            }
            .addOnCanceledListener {
                barcodeValue.text = "Scan Cancelled"
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Barcode scanning failed", Toast.LENGTH_SHORT).show()
            }
    }
}
