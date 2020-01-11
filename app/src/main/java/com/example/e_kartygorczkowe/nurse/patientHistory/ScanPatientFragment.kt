package com.example.e_kartygorczkowe.nurse.patientHistory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.e_kartygorczkowe.MainActivity

import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.MeasurementsHistoryFragmentBinding
import com.example.e_kartygorczkowe.databinding.ScanPatientFragmentBinding

class ScanPatientFragment : Fragment() {

    companion object {
        fun newInstance() = ScanPatientFragment()
    }

    private lateinit var viewModel: ScanPatientViewModel
    private lateinit var binding: ScanPatientFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.scan_patient_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).tagId = null
        viewModel = ViewModelProviders.of(this).get(ScanPatientViewModel::class.java)

        viewModel.patientExists.observe(this, Observer<Boolean> {patientExists ->
            onPatientChecked(patientExists)
        })
    }

    private fun onPatientChecked(exists: Boolean) {
        if (exists) {
            binding.textviewScanPatient.text = "Patient is recognized"
            binding.imgScan.setImageResource(R.drawable.ok)

            findNavController().navigate(R.id.action_scanPatientFragment_to_patientHistoryFragment)
        } else {
            Toast.makeText(
                context,
                "Patient with this id is not recognized",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).tagId?.let { tagId ->
            viewModel.checkIfPatientWithIdExists(tagId)
        }
    }

}
