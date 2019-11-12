package com.example.e_kartygorczkowe.repository

import com.example.e_kartygorczkowe.entity.User
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class FirebaseRepository {
    private val dbInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addUser(user: User) {
        dbInstance.collection("Doctors")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Timber.e(e)
            }
    }
}