package com.example.e_kartygorczkowe.chooseUser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.addFragment
import com.example.e_kartygorczkowe.databinding.ChooseUserFragmentBinding
import com.example.e_kartygorczkowe.login.LoginFragment
import com.example.e_kartygorczkowe.register.RegisterFragment
import com.example.e_kartygorczkowe.replaceFragment

class ChooseUserFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseUserFragment()
    }

    private lateinit var viewModel: ChooseUserViewModel
    private lateinit var binding: ChooseUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.choose_user_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChooseUserViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            val loginFragment = LoginFragment.newInstance()
            (activity as AppCompatActivity).replaceFragment(loginFragment,  R.id.main_frame)
        }

        binding.btnRegisterUser.setOnClickListener {
            val registerFragment = RegisterFragment.newInstance()
            (activity as AppCompatActivity).replaceFragment(registerFragment,  R.id.main_frame)
        }
    }

}
