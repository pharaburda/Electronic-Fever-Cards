package com.example.e_kartygorczkowe.doctor.addPatient

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
import com.example.e_kartygorczkowe.databinding.AddPatientFragmentBinding
import com.example.e_kartygorczkowe.entity.Patient
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.UserType

class AddPatientFragment : Fragment() {

    companion object {
        fun newInstance() = AddPatientFragment()
    }

    private lateinit var viewModel: AddPatientViewModel
    private lateinit var binding: AddPatientFragmentBinding
    private val patient = Patient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_patient_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).tagId = null
        viewModel = ViewModelProviders.of(this).get(AddPatientViewModel::class.java)
        viewModel.state.observe(this, Observer<State> { state ->
            onStateChanged(state)
        })
        binding.patient = this.patient
        binding.btnAddPatient.setOnClickListener {
            viewModel.addPatient(patient)
        }
    }

    private fun onStateChanged(state: State) {
        when (state) {
            is State.Success -> {
                Toast.makeText(
                    context,
                    "Adding patient succeded",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }
            is State.Error -> {
                Toast.makeText(
                    context,
                    "Login failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if ((activity as MainActivity).tagId != null) {
            binding.textviewScanPatient.text = "Patient is recognized"
            this.patient.id = (activity as MainActivity).tagId!!
        }
    }
}
