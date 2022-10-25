package com.example.studyandroid.model

import javax.inject.Inject

class Repository @Inject constructor(private val remoteServiceHelper: RemoteServiceHelper) {
    suspend fun getHeroes() = remoteServiceHelper.getHeroes()

}