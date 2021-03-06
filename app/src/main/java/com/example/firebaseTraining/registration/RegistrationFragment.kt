package com.example.firebaseTraining.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.firebaseTraining.BaseFragment
import com.example.firebaseTraining.databinding.FragmentSignUpBinding
import com.example.firebaseTraining.profile.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User

class RegistrationFragment : BaseFragment() {
    companion object {
        const val REG_DEBUG = "REG DEBUG"
    }
    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val registrationViewModel by viewModels<RegistrationViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSignUpClick()
    }

    private fun setupSignUpClick() {
        binding.signUpButtonRegistration.setOnClickListener{
            val email = binding.emailRegistration.text?.trim().toString()
            val password = binding.passRegistration.text?.trim().toString()
            val repeatPassword = binding.repeatPassRegistration.text?.trim().toString()

            if (password==repeatPassword) {
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener {authRes ->
                            if (authRes.user != null) {
                                val user = com.example.firebaseTraining.data.User(
                                        authRes.user!!.uid,
                                        "",
                                        "",
                                        authRes.user!!.email,
                                        listOf(),
                                        ""
                                        )
                                registrationViewModel.createNewUser(user)
                                startApp()
                            }
                        }
                        .addOnFailureListener { exc ->
                            Snackbar.make(requireView(),"Something went wrong", Snackbar.LENGTH_SHORT).show()
                            Log.d(REG_DEBUG, exc.toString())
                        }
                    }
        }
    }
}
