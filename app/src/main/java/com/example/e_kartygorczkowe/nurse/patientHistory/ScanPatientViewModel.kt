package com.example.e_kartygorczkowe.nurse.patientHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.Patient
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

class ScanPatientViewModel : ViewModel() {
    private val repository: DatabaseRepository = DatabaseRepository()

    val patientExists: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun checkIfPatientWithIdExists(id: String) {
        repository.getPatientWithId(id).subscribe(
            object : MaybeObserver<Patient> {
                override fun onSuccess(t: Patient?) {
                    patientExists.value = true
                }

                override fun onSubscribe(d: Disposable?) {
                    // do nothing
                }

                override fun onComplete() {
                    patientExists.value = false
                }

                override fun onError(e: Throwable?) {
                    patientExists.value = false
                }
            }
        )
    }
}
