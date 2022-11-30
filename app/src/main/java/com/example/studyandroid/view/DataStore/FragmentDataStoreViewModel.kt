package com.example.studyandroid.view.DataStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyandroid.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentDataStoreViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _myNumberLiveData = MutableLiveData<Int>()
    val myNumberLiveData: LiveData<Int>
        get() = _myNumberLiveData

    fun increaseMyNumber() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                repository.getMyDataStore().writePreferenceDataStoreMyNumber()
                readMyNumber()
            }
        }
    }

    private fun readMyNumber() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                _myNumberLiveData.postValue(repository.getMyDataStore().readPreferenceDataStoreMyNumber().first())
            }
        }
    }

    init {
        readMyNumber()
    }
}