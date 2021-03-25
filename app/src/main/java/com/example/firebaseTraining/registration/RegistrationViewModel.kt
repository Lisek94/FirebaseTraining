package com.example.firebaseTraining.registration

import androidx.lifecycle.ViewModel
import com.example.firebaseTraining.data.User
import com.example.firebaseTraining.repository.FirebaseRepository

class RegistrationViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: User) {
        repository.createNewUser(user)
    }
}