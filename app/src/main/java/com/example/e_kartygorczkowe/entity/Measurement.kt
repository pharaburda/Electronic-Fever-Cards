package com.example.e_kartygorczkowe.entity

import com.google.firebase.Timestamp

data class Measurement (
    var timestamp: Timestamp? = Timestamp.now(),
    var temperature: String? = "0",
    var nurse: User = User(),
    var patient: Patient = Patient()
)