package com.example.qrtest2

import android.os.Bundle
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

    private fun handleScanCompleted(code: String?, barcodeValue: TextView) {
        val barcodeValue = findViewById<TextView>(R.id.barcode_value)
        barcodeValue.text = code
    }

    private fun handleScanFailed() {
        Toast.makeText(this, "Barcode scanning failed", Toast.LENGTH_SHORT).show()
    }

    private fun scanCode() {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_EAN_13)
            .build()
        val scanner = GmsBarcodeScanning.getClient(this, options)
        val barcodeValue = findViewById<TextView>(R.id.barcode_value)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                if (barcode.rawValue != null) {
                    handleScanCompleted(barcode.rawValue, barcodeValue)
                }
                else {
                    handleScanFailed()
                }
            }
            .addOnCanceledListener {
                barcodeValue.text = "Scan cancelled"
            }
            .addOnFailureListener { e ->
                handleScanFailed()
            }
    }
