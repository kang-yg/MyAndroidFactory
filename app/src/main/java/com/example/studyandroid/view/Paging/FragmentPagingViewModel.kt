package com.example.studyandroid.view.Paging

import androidx.lifecycle.ViewModel
import com.example.studyandroid.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentPagingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun getLoremImages(limit: Int) = repository.getLoremImages(limit)
}