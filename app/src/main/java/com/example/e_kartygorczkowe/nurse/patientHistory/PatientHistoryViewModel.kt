package com.example.e_kartygorczkowe.nurse.patientHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.Measurement
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import timber.log.Timber

class PatientHistoryViewModel : ViewModel() {
    private val repository: DatabaseRepository = DatabaseRepository()

    val measurementsHistory: MutableLiveData<List<Measurement>> by lazy {
        MutableLiveData<List<Measurement>>()
    }

    fun getPatientHistory(patientId: String) {
        repository.getMeasurementsHistory(patientId).subscribe(
            object : DisposableMaybeObserver<List<Measurement>>() {
                override fun onSuccess(t: List<Measurement>?) {
                    Timber.d("Got mes $t")
                    measurementsHistory.value = t
                }

                override fun onComplete() {
                    // do nothing
                }

                override fun onError(e: Throwable?) {
                    Timber.e(e)
                }
            }
        )
    }
}
