package com.example.bfaa2_exca.connect

import androidx.lifecycle.liveData
import com.example.bfaa2_exca.extra.Resource
import kotlinx.coroutines.Dispatchers



object PersonRetrofit {
    fun searchUsers(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val userSearch = RetroConfig.apiClient.searchUsers(query)
            emit(Resource.success(userSearch.items))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error"))
        }
    }

    fun getProfileUser(username:String) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(RetroConfig.apiClient.userDetail(username)))
        }
        catch (exception: Exception){
            emit(Resource.error(null, exception.message?:"Error"))
        }
    }
    fun getFollower(username:String)= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(RetroConfig.apiClient.userFollower(username)))
        }
        catch (exception:Exception){
            emit(Resource.error(null,exception.message?:"Error"))
        }
    }

    fun getFollowing(username:String)= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(RetroConfig.apiClient.userFollowing(username)))
        }
        catch (exception:Exception){
            emit(Resource.error(null,exception.message?:"Error"))
        }
    }
}