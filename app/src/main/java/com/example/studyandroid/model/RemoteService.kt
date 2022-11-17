package com.example.studyandroid.model

import com.example.studyandroid.view.MotionLayout.FragmentMotionLayoutRecyclerViewItemResponse
import com.example.studyandroid.view.Paging.LoremImageInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    // https://run.mocky.io/v3/30d27bf0-027d-47e7-8b28-45d9744f8c50
    @GET("/v3/30d27bf0-027d-47e7-8b28-45d9744f8c50")
    suspend fun getHeroes(): Response<FragmentMotionLayoutRecyclerViewItemResponse>

    @GET("/v2/list")
    suspend fun getLoremImages(@Query("page") page: Int, @Query("limit") limit: Int): Response<List<LoremImageInfo>>
}