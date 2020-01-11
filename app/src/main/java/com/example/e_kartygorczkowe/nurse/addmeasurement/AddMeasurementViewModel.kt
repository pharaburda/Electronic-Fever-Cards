package com.example.e_kartygorczkowe.nurse.addmeasurement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.Measurement
import com.example.e_kartygorczkowe.entity.Patient
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

class AddMeasurementViewModel : ViewModel() {
    private val repository: DatabaseRepository = DatabaseRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    val patient: MutableLiveData<Patient?> by lazy {
        MutableLiveData<Patient?>()
    }

    fun addMeasurement(measurement: Measurement) {
        repository.addMeasaurement(measurement).subscribe(
            object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                    // do nothing
                }

                override fun onComplete() {
                    state.value = State.Success
                }

                override fun onError(e: Throwable?) {
                    state.value = State.Error
                }
            }
        )
    }

    fun getPatient(id: String) {
        repository.getPatientWithId(id).subscribe(
            object : MaybeObserver<Patient> {
                override fun onSuccess(t: Patient?) {
                    patient.value = t
                }

                override fun onSubscribe(d: Disposable?) {
                    // do nothing
                }

                override fun onComplete() {
                    patient.value = null
                }

                override fun onError(e: Throwable?) {
                    patient.value = null
                }
            }
        )
    }
}
