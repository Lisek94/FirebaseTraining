package com.example.firebaseTraining.profile

import androidx.lifecycle.ViewModel
import com.example.firebaseTraining.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val user = repository.getUserData()
}