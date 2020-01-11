package com.example.e_kartygorczkowe.nurse.patientHistory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.e_kartygorczkowe.R

class PatientHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = PatientHistoryFragment()
    }

    private lateinit var viewModel: PatientHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.patient_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PatientHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
