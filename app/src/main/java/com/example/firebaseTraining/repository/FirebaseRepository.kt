package com.example.firebaseTraining.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebaseTraining.data.Car
import com.example.firebaseTraining.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseRepository {
    companion object {
        const val REPO_DEBUG = "REPOSITORY DEBUG"
    }

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()

    fun getUserData():LiveData<User> {
        val closeResult = MutableLiveData<User>()
        val uid = auth.currentUser?.uid

        cloud.collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                closeResult.postValue(user!!)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG,it.message.toString())
            }
        return closeResult
    }

    fun getCars():LiveData<List<Car>> {
        val closeResult = MutableLiveData<List<Car>>()

        cloud.collection("cars")
            .get()
            .addOnSuccessListener {
                val user = it.toObjects(Car::class.java)
                closeResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG,it.message.toString())
            }
        return closeResult
    }
}