package com.example.e_kartygorczkowe.repository

import com.example.e_kartygorczkowe.entity.Measurement
import com.example.e_kartygorczkowe.entity.Patient
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.entity.UserType
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import timber.log.Timber

class DatabaseRepository {
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

    fun login(user: User, id: String): Maybe<User> = Maybe.create { emitter ->
        val collectionPath = when (user.userType) {
            UserType.Doctor -> "Doctors"
            UserType.Nurse -> "Nurses"
            UserType.None -> ""
        }
        dbInstance.collection(collectionPath)
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    emitter.onComplete()
                } else {
                    emitter.onSuccess(documents.first().toObject(User::class.java))
                }
            }
            .addOnFailureListener { exception ->
                Timber.e(exception)
                emitter.onError(exception)
            }
    }

    fun getMeasurementsHistory(): Maybe<List<Measurement>> =
        Maybe.create { emitter ->
            dbInstance.collection("Measurements")
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        emitter.onComplete()
                    } else {
                        emitter.onSuccess(documents.toObjects(Measurement::class.java))
                    }
                }
                .addOnFailureListener { exception ->
                    Timber.e(exception)
                    emitter.onError(exception)
                }
        }

    fun getMeasurementsHistory(patientId: String): Maybe<List<Measurement>> =
        Maybe.create { emitter ->
            dbInstance.collection("Measurements")
                .whereEqualTo("patientId", patientId)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        emitter.onComplete()
                    } else {
                        emitter.onSuccess(documents.toObjects(Measurement::class.java))
                    }
                }
                .addOnFailureListener { exception ->
                    Timber.e(exception)
                    emitter.onError(exception)
                }
        }

    fun addPatient(patient: Patient): Completable =
        Completable.create { emitter ->
            dbInstance.collection("Patients")
                .add(patient)
                .addOnSuccessListener { documentReference ->
                    Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
                    emitter.onComplete()
                }
                .addOnFailureListener { e ->
                    Timber.e(e)
                    emitter.onError(e)
                }

        }

    fun checkIfPatientExists(id: String): Maybe<Boolean> =
        Maybe.create { emitter ->
            dbInstance.collection("Patients")
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        emitter.onComplete()
                    } else {
                        emitter.onSuccess(true)
                    }
                }
                .addOnFailureListener { exception ->
                    Timber.e(exception)
                    emitter.onError(exception)
                }
        }

    fun addMeasaurement(measurement: Measurement): Completable =
        Completable.create { emitter ->
            dbInstance.collection("Measurements")
                .add(measurement)
                .addOnSuccessListener { documentReference ->
                    Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
                    emitter.onComplete()
                }
                .addOnFailureListener { e ->
                    Timber.e(e)
                    emitter.onError(e)
                }

        }

}