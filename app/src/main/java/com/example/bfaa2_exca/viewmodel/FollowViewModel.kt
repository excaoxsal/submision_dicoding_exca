package com.example.bfaa2_exca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.bfaa2_exca.connect.PersonRetrofit
import com.example.bfaa2_exca.extra.Resource
import com.example.bfaa2_exca.model.PersonModel

class FollowViewModel: ViewModel() {
    private val username: MutableLiveData<String> = MutableLiveData()
    private lateinit var type: TypeView
    val dataFollow : LiveData<Resource<List<PersonModel>>> = Transformations
        .switchMap(username) {
            when (type) {
                TypeView.FOLLOWER -> {
                    PersonRetrofit.getFollower(it)
                }
                TypeView.FOLLOWING -> {
                    PersonRetrofit.getFollowing(it)
                }
            }
        }
    fun setFollow(user:String,typeView: TypeView){
        if (username.value == user) {
            return
        }
        username.value = user
        type = typeView
    }

}

enum class TypeView {
    FOLLOWER,
    FOLLOWING,
}