package com.example.e_kartygorczkowe.nurse.addmeasurement

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
import com.example.e_kartygorczkowe.databinding.AddMeasurementFragmentBinding
import com.example.e_kartygorczkowe.entity.Measurement
import com.example.e_kartygorczkowe.entity.State
import com.google.firebase.Timestamp

class AddMeasurementFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddMeasurementFragment()
    }

    private lateinit var viewModel: AddMeasurementViewModel
    private lateinit var binding: AddMeasurementFragmentBinding
    private val measurement = Measurement()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_measurement_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).tagId = null
        viewModel = ViewModelProviders.of(this).get(AddMeasurementViewModel::class.java)
        viewModel.state.observe(this, Observer<State> { state ->
            onStateChanged(state)
        })
        viewModel.patientExists.observe(this, Observer<Boolean> {patientExists ->
            onPatientChecked(patientExists)
        })
        binding.measurement = this.measurement
        binding.btnAddMeasurement.setOnClickListener {
            this.measurement.timestamp = Timestamp.now()
            viewModel.addMeasurement(this.measurement)
        }
    }

    private fun onPatientChecked(exists: Boolean) {
        if (exists) {
            binding.textviewScanPatient.text = "Patient is recognized"
            binding.imgScan.setImageResource(R.drawable.ok)
            this.measurement.patientId = (activity as MainActivity).tagId!!
        } else {
            Toast.makeText(
                context,
                "Patient with this id is not recognized",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onStateChanged(state: State) {
        when (state) {
            is State.Success -> {
                Toast.makeText(
                    context,
                    "Adding measurement succeded",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }
            is State.Error -> {
                Toast.makeText(
                    context,
                    "Adding measurement failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).tagId?.let { tagId ->
            viewModel.checkIfPatientWithIdExists(tagId)
        }
    }

}
