package com.example.e_kartygorczkowe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_kartygorczkowe.chooseUser.ChooseUserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chooseUserFragment = ChooseUserFragment.newInstance()
        addFragment(chooseUserFragment, R.id.main_frame)
    }

}
