package com.example.e_kartygorczkowe.entity

import com.google.firebase.Timestamp

data class Measurement (
    val timestamp: Timestamp = Timestamp.now(),
    val temperature: Int = 0,
    val nurse: User = User(),
    val patient: Patient = Patient()
)