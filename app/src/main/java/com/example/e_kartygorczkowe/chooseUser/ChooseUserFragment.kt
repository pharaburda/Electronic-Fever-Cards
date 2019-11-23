package com.example.e_kartygorczkowe.chooseUser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.databinding.ChooseUserFragmentBinding

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
            findNavController().navigate(R.id.action_chooseUserFragment_to_loginFragment)
        }

        binding.btnRegisterUser.setOnClickListener {
            findNavController().navigate(R.id.action_chooseUserFragment_to_registerFragment)
        }
    }
}
