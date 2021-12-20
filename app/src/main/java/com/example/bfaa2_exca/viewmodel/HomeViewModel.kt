package com.example.bfaa2_exca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.example.bfaa2_exca.connect.PersonRetrofit
import com.example.bfaa2_exca.model.PersonModel

class HomeViewModel : ViewModel() {
    private val username: MutableLiveData<String> = MutableLiveData()

    val searchPerson : LiveData<Resource<List<PersonModel>>> = Transformations
        .switchMap(username){
            PersonRetrofit.searchUsers(it)
        }
    fun setSearch(query:String){
        if (username.value==query){
            return
        }
        username.value=query
    }
}