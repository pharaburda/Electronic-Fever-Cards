package com.example.e_kartygorczkowe.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.FirebaseRepository
import io.reactivex.rxjava3.observers.DisposableMaybeObserver

class LoginViewModel : ViewModel() {

    private val repository: FirebaseRepository = FirebaseRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    fun login(user: User) {
        repository.login(user).subscribe(
            object : DisposableMaybeObserver<User>() {
                override fun onSuccess(t: User?) {
                    state.value = State.Success
                }

                override fun onComplete() {
                    state.value = State.Error
                }

                override fun onError(e: Throwable?) {
                    state.value = State.Error
                }
            }
        )
    }
}
