package com.example.e_kartygorczkowe.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.repository.FirebaseRepository

class RegisterViewModel : ViewModel() {
    private val repository: FirebaseRepository = FirebaseRepository()
    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    fun register(user: User) {
        repository.addUser(user)
    }

}

sealed class State {
    object Success : State()
    object Error : State()
}
