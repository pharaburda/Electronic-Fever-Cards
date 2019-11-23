package com.example.e_kartygorczkowe.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.RegisterFragmentBinding
import com.example.e_kartygorczkowe.entity.User
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.UserType

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: RegisterFragmentBinding
    private var user = User("", "", UserType.None, "", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.register_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        viewModel.state.observe(this, Observer<State> { state ->
            onStateChanged(state)
        })
        binding.user = this.user
        binding.btnRegister.setOnClickListener { viewModel.register(user) }
    }

    private fun onStateChanged(state: State) = when (state) {
        is State.Success -> {
            Toast.makeText(
                context,
                "Registration succeded",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }
        is State.Error -> {
            Toast.makeText(
                context,
                "Some error occurred",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
