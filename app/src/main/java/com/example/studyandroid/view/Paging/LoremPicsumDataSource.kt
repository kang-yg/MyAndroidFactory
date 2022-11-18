package com.example.studyandroid.view.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.studyandroid.model.RemoteServiceHelper
import retrofit2.Response

class LoremPicsumDataSource(private val remoteServiceHelper: RemoteServiceHelper, private val limit: Int) : PagingSource<Int, LoremImageInfo>() {
    override fun getRefreshKey(state: PagingState<Int, LoremImageInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LoremImageInfo> {
        return try {
            val pageIndex = params.key ?: 1
            var result: Response<List<LoremImageInfo>>? = null
            result = remoteServiceHelper.getLoremImages(pageIndex, limit)
            result?.let {
                if (it.isSuccessful) {
                    LoadResult.Page(
                        data = if (it.isSuccessful && it.body() != null) it.body()!!.toList() else listOf<LoremImageInfo>(),
                        prevKey = if (pageIndex == 1) null else pageIndex - 1,
                        nextKey = if (it.body() == null) null else if (it.body()!!.isEmpty()) null else pageIndex + 1
                    )
                } else {
                    throw IllegalStateException()
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}