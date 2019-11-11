package com.example.e_kartygorczkowe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        // Create a new user with a first and last name
        val user = hashMapOf(
                "Imię" to "Ada",
                "Nazwisko" to "Nowak"
        )

        // Add a new document with a generated ID
        db.collection("Lekarze")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Timber.d("Adding failed")
                    Timber.e(e)
                }
    }

}
