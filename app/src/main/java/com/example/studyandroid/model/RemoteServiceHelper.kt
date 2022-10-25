package com.example.studyandroid.model

import com.example.studyandroid.view.MotionLayout.FragmentMotionLayoutRecyclerViewItemResponse
import retrofit2.Response

interface RemoteServiceHelper {
    suspend fun getHeroes(): Response<FragmentMotionLayoutRecyclerViewItemResponse>
}