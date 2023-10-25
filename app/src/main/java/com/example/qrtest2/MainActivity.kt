package com.example.qrtest2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning

class MainActivity : AppCompatActivity() {
    private lateinit var scanner: BarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanner = BarcodeScanning.getClient()

        val btnScanEan = findViewById<Button>(R.id.btn_scan_ean)
        btnScanEan.setOnClickListener {
            startScan()
        }
    }

    private fun startScan() {
        // Assuming a method to get the InputImage instance
        val image = getInputImage()

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    val rawValue = barcode.rawValue
                    Toast.makeText(this, "EAN: $rawValue", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Scan failed", Toast.LENGTH_SHORT).show()
            }
    }
}
