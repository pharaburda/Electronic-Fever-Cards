package com.example.e_kartygorczkowe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    var tagId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = findNavController(R.id.nav_host_fragment)
    }

    override fun onResume() {
        super.onResume()
        tagId = intent.extras?.getString("tagId")

        Toast.makeText(
            this,
            "$tagId",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
