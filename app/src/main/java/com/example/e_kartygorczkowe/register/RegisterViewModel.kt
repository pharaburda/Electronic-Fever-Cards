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
import timber.log.Timber

class RegisterViewModel : ViewModel() {
    private val databaseRepository: DatabaseRepository = DatabaseRepository()
    private val authenticationRepository: AuthenticationRepository = AuthenticationRepository()
    val state: MutableLiveData<Pair<State, String>> by lazy {
        MutableLiveData<Pair<State, String>>()
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
                        // do nothing
                    }

                    override fun onError(e: Throwable?) {
                        Timber.d(e)
                        state.value = Pair(State.Error, e?.message ?: "")
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
                name = user.name,
                surname = user.surname,
                id = id
            )
        ).subscribe(
            object : CompletableObserver {
                override fun onComplete() {
                    state.value = Pair(State.Success, "")
                }

                override fun onError(e: Throwable?) {
                    state.value = Pair(State.Error, e?.message ?: "")
                }

                override fun onSubscribe(d: Disposable?) {
                    // do nothing
                }
            }
        )
    }

}
