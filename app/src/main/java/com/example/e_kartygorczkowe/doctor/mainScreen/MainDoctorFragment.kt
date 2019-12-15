package com.example.e_kartygorczkowe.doctor.mainScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.MainDoctorFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class MainDoctorFragment : Fragment() {

    companion object {
        fun newInstance() = MainDoctorFragment()
    }

    private lateinit var viewModel: MainDoctorViewModel
    private lateinit var binding: MainDoctorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.main_doctor_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainDoctorViewModel::class.java)
        binding.btnMeasurementsHistory.setOnClickListener {
            findNavController().navigate(R.id.action_mainDoctorFragment_to_measurementsHistoryFragment)
        }
        binding.btnAddPatient.setOnClickListener {
            findNavController().navigate(R.id.action_mainDoctorFragment_to_addPatientFragment)
        }
        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().popBackStack()
        }
    }

}
