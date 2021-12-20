package com.example.bfaa2_exca.connect

import com.example.bfaa2_exca.model.PersonModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") q: String?
    ): SearchReply

    @GET("users/{username}")
    suspend fun userDetail(
        @Path("username") username: String?
    ): PersonModel

    @GET("users/{username}/followers")
    suspend fun userFollower(
        @Path("username") username: String?
    ): List<PersonModel>

    @GET("users/{username}/following")
    suspend fun userFollowing(
        @Path("username") username: String?
    ): List<PersonModel>
}