package com.example.studyandroid.model

import com.example.studyandroid.view.MotionLayout.FragmentMotionLayoutRecyclerViewItemResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteServiceHelperImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteServiceHelper {
    override suspend fun getHeroes(): Response<FragmentMotionLayoutRecyclerViewItemResponse> = remoteService.getHeroes()
}