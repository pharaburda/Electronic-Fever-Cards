package com.example.e_kartygorczkowe.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.e_kartygorczkowe.MainActivity
import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.LoginFragmentBinding
import com.example.e_kartygorczkowe.entity.State
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.entity.UserType
import com.example.e_kartygorczkowe.replaceFragment


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private var user = User("", "", UserType.None, "", "")

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
        viewModel.state.observe(this, Observer<State> { state ->
            onStateChanged(state)
        })
        binding.user = this.user
        binding.btnLogin.setOnClickListener { viewModel.login(user) }
    }

    private fun onStateChanged(state: State) = when (state) {
        is State.Success -> {
            Toast.makeText(
                context,
                "Login succeded",
                Toast.LENGTH_SHORT
            ).show()
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
