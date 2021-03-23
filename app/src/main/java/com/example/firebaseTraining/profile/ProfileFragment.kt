package com.example.firebaseTraining.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.firebaseTraining.R
import com.example.firebaseTraining.data.User
import com.example.firebaseTraining.databinding.FragmentProfileBinding
import com.example.firebaseTraining.databinding.FragmentSignInBinding
import com.example.firebaseTraining.home.HomeViewModel

class ProfileFragment : Fragment() {
    companion object {
        const val PROFILE_DEBUG = "PROFILE DEBUG"
    }

    private val profileViewModel by viewModels<ProfileViewModel>()

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileViewModel.user.observe(viewLifecycleOwner, {user ->
            bindUserData(user)

        })
    }

    private fun bindUserData(user:User) {
        Log.d(PROFILE_DEBUG,user.toString())
    }
}