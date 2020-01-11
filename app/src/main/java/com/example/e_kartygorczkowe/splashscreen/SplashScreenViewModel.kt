package com.example.e_kartygorczkowe.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.AuthenticationRepository
import com.example.e_kartygorczkowe.repository.DatabaseRepository
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber

class SplashScreenViewModel : ViewModel() {
    private val authRepository: AuthenticationRepository = AuthenticationRepository()
    private val databaseRepository: DatabaseRepository = DatabaseRepository()

    var userid: PublishSubject<String> =  PublishSubject.create()

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    fun checkUserLogIn() {
        authRepository.isLoggedIn().subscribe(
            object : DisposableMaybeObserver<String>() {
                override fun onSuccess(t: String?) {
                    userid.onNext(t)
                }

                override fun onComplete() {
                    // do nothing
                }

                override fun onError(e: Throwable?) {
                    Timber.i(e)
                    userid.onNext("")
                }
            }
        )
    }

    fun getUserWithId(id: String) {
        databaseRepository.getUserWithId(id).subscribe(
            object : DisposableMaybeObserver<User>() {
                override fun onSuccess(t: User?) {
                    user.value = t
                }

                override fun onComplete() {
                    user.value = null
                }

                override fun onError(e: Throwable?) {
                    Timber.e(e)
                }
            }
        )
    }
}
