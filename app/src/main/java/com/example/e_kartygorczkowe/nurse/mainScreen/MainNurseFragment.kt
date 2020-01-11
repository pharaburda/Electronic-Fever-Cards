package com.example.e_kartygorczkowe.nurse.mainScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.MainNurseFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class MainNurseFragment : Fragment() {

    companion object {
        fun newInstance() = MainNurseFragment()
    }

    private lateinit var viewModel: MainNurseViewModel
    private lateinit var binding: MainNurseFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.main_nurse_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainNurseViewModel::class.java)

        binding.btnPatientMeasurementsHistory.setOnClickListener {
            findNavController().navigate(R.id.action_mainNurseFragment_to_patientHistoryFragment)
        }

        binding.btnAddMeasurement.setOnClickListener {
            findNavController().navigate(R.id.action_mainNurseFragment_to_addMeasurementFragment)
        }

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().popBackStack()
        }
    }

}
