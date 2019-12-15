package com.example.e_kartygorczkowe.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.AuthenticationRepository
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

class RegisterViewModel : ViewModel() {
    private val databaseRepository: DatabaseRepository = DatabaseRepository()
    private val authenticationRepository: AuthenticationRepository = AuthenticationRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    fun register(user: User) {
        user.email.trimEnd()
        user.password.trimEnd()

        authenticationRepository.register(user)
            .subscribe(
                object : MaybeObserver<String> {
                    override fun onSuccess(t: String?) {
                        t?.let {
                            addUserToDatabase(user, it)
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

    private fun addUserToDatabase(user: User, id: String) {
        databaseRepository.addUser(
            User(
                userType = user.userType,
                email = user.email,
                name = user.name,
                surname = user.surname,
                id = id
            )
        ).subscribe(
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
