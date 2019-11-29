package com.example.e_kartygorczkowe.doctor.measurementsHistory

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
import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.MeasurementsHistoryFragmentBinding
import com.example.e_kartygorczkowe.entity.Measurement
import kotlinx.android.synthetic.main.measurement_view.view.*

class MeasurementsHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = MeasurementsHistoryFragment()
    }

    private lateinit var viewModel: MeasurementsHistoryViewModel
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
        viewModel = ViewModelProviders.of(this).get(MeasurementsHistoryViewModel::class.java)
        viewModel.measurementsHistory.observe(this, Observer<List<Measurement>> { measurements ->
            fillMeasurementsList(measurements)
        })
        viewModel.getPatientsHistory()
    }

    private fun fillMeasurementsList(measurements: List<Measurement>) {
        binding.recVPatientsHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recVPatientsHistory.adapter = MeasurementsAdapter(measurements)
    }

    class MeasurementsAdapter(private val items:  List<Measurement>) :
        RecyclerView.Adapter<MeasurementsAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.measurement_view,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.temperature.text = items[position].temperature.toString()
            holder.timeStamp.text = items[position].timestamp?.toDate().toString()
            holder.patientName.text = items[position].patient?.name
            holder.nurseName.text = items[position].nurse?.name
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val temperature: TextView = view.txt_temperature
            val timeStamp: TextView = view.txt_timestamp
            val patientName: TextView = view.txt_patient
            val nurseName: TextView = view.txt_nurse
        }
    }

}
