package com.example.e_kartygorczkowe.doctor.addPatient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.Patient
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable

class AddPatientViewModel : ViewModel() {
    private val repository: DatabaseRepository = DatabaseRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    fun addPatient(patient: Patient) {
        repository.addPatient(patient).subscribe(
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
}
