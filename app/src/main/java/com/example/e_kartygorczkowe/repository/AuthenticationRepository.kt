package com.example.e_kartygorczkowe.repository

import com.example.e_kartygorczkowe.entity.User
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Maybe
import timber.log.Timber

class AuthenticationRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(user: User): Maybe<String> =
        Maybe.create { emitter ->
            auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Timber.d("Successfully Registered")
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    emitter.onSuccess(currentUser?.uid)
                } else {
                    Timber.d("Registration Failed")
                    emitter.onError(task.exception)
                }
            }
        }

    fun login(user: User): Maybe<String> = Maybe.create { emitter ->
        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Timber.d("Successfully Logged In")
                val currentUser = FirebaseAuth.getInstance().currentUser
                emitter.onSuccess(currentUser?.uid)
            } else {
                Timber.d("Login Failed")
                emitter.onError(task.exception)
            }
        }
    }
}