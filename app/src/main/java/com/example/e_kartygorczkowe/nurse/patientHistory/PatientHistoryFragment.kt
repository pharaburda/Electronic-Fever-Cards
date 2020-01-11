package com.example.e_kartygorczkowe.nurse.patientHistory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_kartygorczkowe.MainActivity

import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.MeasurementsHistoryFragmentBinding
import com.example.e_kartygorczkowe.doctor.measurementsHistory.MeasurementsHistoryFragment
import com.example.e_kartygorczkowe.entity.Measurement
import kotlinx.android.synthetic.main.measurement_view.view.*

class PatientHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = PatientHistoryFragment()
    }

    private lateinit var viewModel: PatientHistoryViewModel
    private lateinit var binding: MeasurementsHistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.measurements_history_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PatientHistoryViewModel::class.java)

        viewModel.measurementsHistory.observe(this, Observer<List<Measurement>> { measurements ->
            fillMeasurementsList(measurements)
        })
        viewModel.getPatientHistory((activity as MainActivity).tagId ?: "")
    }

    private fun fillMeasurementsList(measurements: List<Measurement>) {
        binding.recVPatientsHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recVPatientsHistory.adapter =
            MeasurementsHistoryFragment.MeasurementsAdapter(measurements)
    }
}
