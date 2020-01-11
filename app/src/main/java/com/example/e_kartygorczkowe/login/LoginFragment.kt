package com.example.e_kartygorczkowe.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.e_kartygorczkowe.MainActivity
import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.LoginFragmentBinding
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.entity.UserType
import io.reactivex.rxjava3.subjects.PublishSubject

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private var user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.state = PublishSubject.create()
        viewModel.state.subscribe {onStateChanged(it)}

        this.user = User()
        binding.user = this.user

        binding.btnLogin.setOnClickListener {
            viewModel.login(user)
        }
    }

    private fun onStateChanged(state: Pair<State, User>) {
        when (state.first) {
            is State.Success -> {
                Toast.makeText(
                    context,
                    "Login succeded",
                    Toast.LENGTH_SHORT
                ).show()
                (activity as MainActivity).user = state.second
                if (this.user.userType == UserType.Doctor) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainDoctorFragment)
                }
                if (this.user.userType == UserType.Nurse) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainNurseFragment)
                }
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
}
