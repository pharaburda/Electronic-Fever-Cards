package com.example.e_kartygorczkowe.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.AuthenticationRepository
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

class LoginViewModel : ViewModel() {
    private val databaseRepository: DatabaseRepository = DatabaseRepository()
    private val authenticationRepository: AuthenticationRepository = AuthenticationRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    fun login(user: User) {
        authenticationRepository.login(user)
            .subscribe(
                object : MaybeObserver<String> {
                    override fun onSuccess(t: String?) {
                        t?.let {
                            getUserFromDatabase(user, it)
                        }
                    }

                    override fun onComplete() {
                        state.value = State.Error
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

    private fun getUserFromDatabase(user: User, id: String) {
        databaseRepository.login(user, id)
        .subscribe(
            object : MaybeObserver<User> {
                override fun onSuccess(t: User?) {
                    state.value = State.Success
                }

                override fun onComplete() {
                    state.value = State.Error
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
