package com.example.bfaa2_exca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.bfaa2_exca.connect.PersonRetrofit
import com.example.bfaa2_exca.extra.Resource
import com.example.bfaa2_exca.model.PersonModel

class ProfileViewModel : ViewModel() {

    private val username: MutableLiveData<String> = MutableLiveData()

    val dataProfile: LiveData<Resource<PersonModel>> = Transformations
        .switchMap(username) {
            PersonRetrofit.getProfileUser(it)
        }

    fun setProfile(userid: String) {
        if (username.value == userid) {
            return
        }
        username.value = userid
    }
}