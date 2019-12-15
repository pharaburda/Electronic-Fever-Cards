package com.example.e_kartygorczkowe.login

import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.AuthenticationRepository
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

class LoginViewModel : ViewModel() {
    private val databaseRepository: DatabaseRepository = DatabaseRepository()
    private val authenticationRepository: AuthenticationRepository = AuthenticationRepository()
    var state: PublishSubject<State> = PublishSubject.create()

    fun login(user: User) {
        user.email.trimEnd()
        user.password.trimEnd()

        authenticationRepository.login(user)
            .subscribe(
                object : MaybeObserver<String> {
                    override fun onSuccess(t: String?) {
                        t?.let {
                            getUserFromDatabase(user, it)
                        }
                    }

                    override fun onComplete() {
                        state.onNext(State.Error)
                    }

                    override fun onError(e: Throwable?) {
                        state.onNext(State.Error)
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
                    state.onNext(State.Success)
                }

                override fun onComplete() {
                    state.onNext(State.Error)
                }

                override fun onError(e: Throwable?) {
                    state.onNext(State.Error)
                }

                override fun onSubscribe(d: Disposable?) {
                    // do nothing
                }
            }
        )
    }
}
