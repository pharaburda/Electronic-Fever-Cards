package com.example.e_kartygorczkowe.entity

sealed class State {
    object Success : State()
    object Error : State()
}