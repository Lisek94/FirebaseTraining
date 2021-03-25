package com.example.firebaseTraining.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseTraining.data.Car
import com.example.firebaseTraining.data.User
import com.example.firebaseTraining.databinding.FragmentProfileBinding
import com.example.firebaseTraining.home.CarAdapter
import com.example.firebaseTraining.home.OnCarItemLongClick

class ProfileFragment : Fragment(), OnCarItemLongClick {
    companion object {
        const val PROFILE_DEBUG = "PROFILE DEBUG"
    }

    private val profileViewModel by viewModels<ProfileViewModel>()
    private val adapter = CarAdapter(this)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFavCars.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFavCars.adapter = adapter
        setupSubmitDataClick()
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

        profileViewModel.getFavCars.observe(viewLifecycleOwner, {list->
            list?.let {
                adapter.setCars(it)
            }
        })
    }

    override fun onCarLongClick(car: Car, position: Int) {
        profileViewModel.removeFavCar(car)
        adapter.removedCar(car,position)
    }

    private fun bindUserData(user:User) {
        Log.d(PROFILE_DEBUG,user.toString())
        binding.userEmailET.text = user.email
        binding.userNameET.setText(user.name)
        binding.userSurnameET.setText(user.surname)

    }

    private fun setupSubmitDataClick() {
        binding.submitDataProfile.setOnClickListener {
            val name = binding.userNameET.text.trim().toString()
            val surname = binding.userSurnameET.text.trim().toString()

            val map = mapOf("name" to name, "surname" to surname)
            profileViewModel.editProfileData(map)
        }
    }
}