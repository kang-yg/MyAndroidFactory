package com.example.studyandroid.view.workmanager

import android.os.Bundle
import android.view.View
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.studyandroid.Constants
import com.example.studyandroid.Constants.notificationPermission
import com.example.studyandroid.Util.requestPermissionLauncher
import com.example.studyandroid.Util.checkPermission
import com.example.studyandroid.databinding.FragmentWorkmanagerBinding
import com.example.studyandroid.view.BaseFragment

class FragmentWorkManager : BaseFragment<FragmentWorkmanagerBinding>(FragmentWorkmanagerBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Android 13 이상부터 Notification에 대한 권한이 필요
        requestPermissionLauncher(fragment = this)?.launch(notificationPermission)

        if (checkPermission(requireActivity(), notificationPermission)) {
            val workerData = workDataOf(Constants.NOTIFICATION_TITLE to "제목", Constants.NOTIFICATION_BODY to "내용")
            val myWorkerRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
                .setInputData(workerData)
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkerRequest)
        }
    }
}