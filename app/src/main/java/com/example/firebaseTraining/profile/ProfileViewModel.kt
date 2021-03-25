package com.example.firebaseTraining.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.firebaseTraining.data.Car
import com.example.firebaseTraining.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val user = repository.getUserData()

    val getFavCars = user.switchMap {
        repository.getFavCars(it.favCars)
    }

    fun removeFavCar(car: Car) {
        repository.removeFavCars(car)
    }

    fun editProfileData(map : Map<String,String>) {
        repository.editProfileData(map)
    }
}