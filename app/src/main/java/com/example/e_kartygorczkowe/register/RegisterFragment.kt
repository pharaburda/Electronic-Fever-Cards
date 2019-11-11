package com.example.e_kartygorczkowe.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_kartygorczkowe.R
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber


class RegisterFragment : Fragment() {
    private val dbInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun addUser() {
        // Create a new user with a first and last name
        val user = hashMapOf(
            "Imię" to "Ada",
            "Nazwisko" to "Nowak"
        )

        // Add a new document with a generated ID
        dbInstance.collection("Lekarze")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Timber.e(e)
            }
    }

}
