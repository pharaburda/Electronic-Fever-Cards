package com.example.e_kartygorczkowe.repository

import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.entity.UserType
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import timber.log.Timber

class FirebaseRepository {
    private val dbInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addUser(user: User): Completable =
        Completable.create { emitter ->
            val collectionPath = when (user.userType) {
                UserType.Doctor -> "Doctors"
                UserType.Nurse -> "Nurses"
                UserType.None -> ""
            }

            dbInstance.collection(collectionPath)
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
                    emitter.onComplete()
                }
                .addOnFailureListener { e ->
                    Timber.e(e)
                    emitter.onError(e)
                }

        }

    fun login(user: User): Maybe<User> = Maybe.create{ emitter ->
        val collectionPath = when (user.userType) {
            UserType.Doctor -> "Doctors"
            UserType.Nurse -> "Nurser"
            UserType.None -> ""
        }
        dbInstance.collection(collectionPath)
            .whereEqualTo("login", user.login)
            .whereEqualTo("password", user.password)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    emitter.onComplete()
                } else {
                    emitter.onSuccess(documents.first() as User)
                }
            }
            .addOnFailureListener { exception ->
                Timber.e(exception)
                emitter.onError(exception)
            }
    }
}