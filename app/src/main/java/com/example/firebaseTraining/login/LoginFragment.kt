package com.example.firebaseTraining.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.firebaseTraining.BaseFragment
import com.example.firebaseTraining.R
import com.example.firebaseTraining.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment() {
    companion object {
        const val LOG_DEBUG = "LOG DEBUG"
    }

    private val firebaseAuth = FirebaseAuth.getInstance()
    private var _binding:FragmentSignInBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginClick()
        setupRegistrationClick()
    }

    private fun setupRegistrationClick() {
        binding.signUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun setupLoginClick() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailLoginInput.text?.trim().toString()
            val password = binding.passLoginInput.text?.trim().toString()
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {authRes ->
                       if (authRes.user != null) startApp()
                    }
                    .addOnFailureListener {exc ->
                        Snackbar.make(requireView(),"Something went wrong",Snackbar.LENGTH_SHORT).show()
                        Log.d(LOG_DEBUG, exc.toString())
                    }
        }

    }
}