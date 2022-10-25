package com.example.studyandroid.view.MotionLayout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyandroid.model.Repository
import com.example.studyandroid.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentMotionLayoutViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _res = MutableLiveData<Resource<FragmentMotionLayoutRecyclerViewItemResponse>>()
    val res: LiveData<Resource<FragmentMotionLayoutRecyclerViewItemResponse>>
        get() = _res

    private fun getHeroes() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        repository.getHeroes().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    init {
        getHeroes()
    }
}