package com.example.studyandroid.view.DataStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyandroid.MyPersonProto
import com.example.studyandroid.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FragmentDataStoreViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _myNumberLiveData = MutableLiveData<Int>()
    val myNumberLiveData: LiveData<Int>
        get() = _myNumberLiveData
    private val _myPersonLiveData = MutableLiveData<MyPersonProto>()
    val myPersonLiveData: LiveData<MyPersonProto>
        get() = _myPersonLiveData

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

    fun updateMyPerson() {
        val name = getRandomName()
        val age: Int = Random.nextInt(0, 99)
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                repository.getMyDataStore().writeProtoDataStore(name, age)
                readMyPerson()
            }
        }
    }

    private fun readMyPerson() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                _myPersonLiveData.postValue(repository.getMyDataStore().readProtoDataStoreMyPerson().first())
            }
        }
    }

    private fun getRandomName(): String {
        val charSet = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..10).map { charSet.random() }.joinToString(separator = "") { "$it" }
    }

    init {
        readMyNumber()
        readMyPerson()
    }
}