package com.example.studyandroid.model

import com.example.studyandroid.view.MotionLayout.FragmentMotionLayoutRecyclerViewItemResponse
import com.example.studyandroid.view.Paging.LoremImageInfo
import retrofit2.Response

interface RemoteServiceHelper {
    suspend fun getHeroes(): Response<FragmentMotionLayoutRecyclerViewItemResponse>
    suspend fun getLoremImages(page: Int, limit: Int): Response<List<LoremImageInfo>>
}