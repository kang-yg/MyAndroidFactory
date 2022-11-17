package com.example.studyandroid.model

import com.example.studyandroid.view.MotionLayout.FragmentMotionLayoutRecyclerViewItemResponse
import com.example.studyandroid.view.Paging.LoremImageInfo
import retrofit2.Response
import javax.inject.Inject

class RemoteServiceHelperImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteServiceHelper {
    override suspend fun getHeroes(): Response<FragmentMotionLayoutRecyclerViewItemResponse> = remoteService.getHeroes()
    override suspend fun getLoremImages(page: Int, limit: Int): Response<List<LoremImageInfo>> = remoteService.getLoremImages(page, limit)
}