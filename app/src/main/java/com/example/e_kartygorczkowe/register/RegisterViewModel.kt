package com.example.e_kartygorczkowe.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.FirebaseRepository
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable

class RegisterViewModel : ViewModel() {
    private val repository: FirebaseRepository = FirebaseRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    fun register(user: User) {
        repository.addUser(user).subscribe (
            object : CompletableObserver {
                override fun onComplete() {
                    state.value = State.Success
                }

                override fun onError(e: Throwable?) {
                    state.value = State.Error
                }

                override fun onSubscribe(d: Disposable?) {
                    // do nothing
                }
            }
        )
    }

}

sealed class State {
    object Success : State()
    object Error : State()
}
