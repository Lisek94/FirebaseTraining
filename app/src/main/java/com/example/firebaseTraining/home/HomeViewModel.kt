package com.example.firebaseTraining.home

import androidx.lifecycle.ViewModel
import com.example.firebaseTraining.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    val cars = repository.getCars()





}