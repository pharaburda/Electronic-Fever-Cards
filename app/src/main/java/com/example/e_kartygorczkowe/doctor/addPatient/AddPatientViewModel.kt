package com.example.e_kartygorczkowe.doctor.addPatient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.Patient
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.FirebaseRepository
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableMaybeObserver

class AddPatientViewModel : ViewModel() {
    private val repository: FirebaseRepository = FirebaseRepository()
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
