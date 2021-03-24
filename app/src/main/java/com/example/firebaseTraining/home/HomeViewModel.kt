package com.example.firebaseTraining.home

import androidx.lifecycle.ViewModel
import com.example.firebaseTraining.data.Car
import com.example.firebaseTraining.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val cars = repository.getCars()

    fun addFavCar(car: Car) {
        repository.addFavCars(car)
    }
}