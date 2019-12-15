package com.example.e_kartygorczkowe.entity

import android.view.View
import android.widget.AdapterView

data class User (
    var email: String = "",
    var password: String = "",
    var userType: UserType = UserType.None,
    var name: String = "",
    var surname: String = "",
    var id: String = ""
) {
    fun onSelectItem(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        this.userType = UserType.valueOf(parent.selectedItem as String)
    }
}

enum class UserType(val value: String) {
    Doctor("Doctor"),
    Nurse("Nurse"),
    None("")
}

