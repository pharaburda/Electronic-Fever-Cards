package com.example.e_kartygorczkowe.splashscreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.e_kartygorczkowe.MainActivity

import com.example.e_kartygorczkowe.R
import com.example.e_kartygorczkowe.entity.User
import com.example.e_kartygorczkowe.entity.UserType
import io.reactivex.rxjava3.subjects.PublishSubject

class SplashScreenFragment : Fragment() {

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        viewModel.userid = PublishSubject.create()
        viewModel.userid.firstElement().subscribe { id ->
            getUser(id)
        }
        viewModel.user.observe(this, Observer<User?> {user ->
            gotUser(user)
        })
        viewModel.checkUserLogIn()
    }

    private fun getUser(id: String) {
        if (id.isEmpty()) {
            findNavController().navigate(R.id.action_splashScreenFragment_to_chooseUserFragment)
        } else {
            viewModel.getUserWithId(id)
        }
    }

    private fun gotUser(user: User?) {
        user?.let { currentUser ->
            (activity as MainActivity).user = currentUser
            if (currentUser.userType == UserType.Doctor) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_mainDoctorFragment)
            }
            if (currentUser.userType == UserType.Nurse) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_mainNurseFragment)
            }
        }
    }

}
