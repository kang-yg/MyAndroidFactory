package com.example.studyandroid.view.workmanager

import android.os.Bundle
import android.view.View
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.studyandroid.Constants
import com.example.studyandroid.databinding.FragmentWorkmanagerBinding
import com.example.studyandroid.view.BaseFragment

class FragmentWorkManager : BaseFragment<FragmentWorkmanagerBinding>(FragmentWorkmanagerBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workerData = workDataOf(Constants.NOTIFICATION_TITLE to "제목", Constants.NOTIFICATION_BODY to "내용")
        val myWorkerRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
            .setInputData(workerData)
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkerRequest)
    }
}