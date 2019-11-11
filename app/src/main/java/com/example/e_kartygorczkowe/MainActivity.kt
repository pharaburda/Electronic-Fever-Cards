package com.example.e_kartygorczkowe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_kartygorczkowe.register.RegisterFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerFragment = RegisterFragment()
        addFragment(registerFragment, R.id.main_frame)
    }

}
