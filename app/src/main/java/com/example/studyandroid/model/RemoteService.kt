package com.example.studyandroid.model

import com.example.studyandroid.view.MotionLayout.FragmentMotionLayoutRecyclerViewItemResponse
import retrofit2.Response
import retrofit2.http.GET

interface RemoteService {
    // https://run.mocky.io/v3/30d27bf0-027d-47e7-8b28-45d9744f8c50

    @GET("/v3/30d27bf0-027d-47e7-8b28-45d9744f8c50")
    suspend fun getHeroes(): Response<FragmentMotionLayoutRecyclerViewItemResponse>
}