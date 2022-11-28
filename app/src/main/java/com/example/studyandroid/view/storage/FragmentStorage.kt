package com.example.studyandroid.view.storage

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.Q
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.studyandroid.Constants
import com.example.studyandroid.R
import com.example.studyandroid.Util.dpToPx
import com.example.studyandroid.Util.getStatusBarHeight
import com.example.studyandroid.databinding.FragmentStorageBinding
import com.example.studyandroid.view.BaseFragment
import java.io.FileOutputStream

class FragmentStorage : BaseFragment<FragmentStorageBinding>(FragmentStorageBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding?.let {
            // StatusBar 높이만큼 패딩 적용.
            it.clFragmentStorage.setPadding(0, requireActivity().getStatusBarHeight(), 0, requireActivity().dpToPx(8))

            initBtMediaStore(it)
            initBtStorageAccessFramework(it)
        }
    }

    private fun initBtMediaStore(binding: FragmentStorageBinding) {
        // MediaStore Android SDK level에 따른 Permission request
        requestPermissionLauncher().launch(Constants.requiredPermissions)

        val contentResolver = requireActivity().contentResolver
        binding.btMediaStore.setOnClickListener {
            if (Build.VERSION.SDK_INT < Q) {
                val inputStream = resources.openRawResource(R.raw.my_image)
                val filePath = "${requireActivity().filesDir}/my_image.jpg"
                val outputStream = FileOutputStream(filePath)
                while (true) {
                    val data = inputStream.read()
                    if (data == -1) break
                    outputStream.write(data)
                }
                inputStream.close()
                outputStream.close()

                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "my_image_less_Q.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/*")
                    put(MediaStore.Images.Media.DATA, filePath)
                }
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            } else {
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "my_image_up_Q.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/*")
                    put(MediaStore.Images.Media.IS_PENDING, 1)
                }

                val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                val item = contentResolver.insert(collection, values)
                item?.let { uri ->
                    contentResolver.openFileDescriptor(uri, "w", null).use { fileDescriptor ->
                        fileDescriptor?.let {
                            FileOutputStream(it.fileDescriptor).use { fileOutputStream ->
                                val imageInputStream = requireActivity().resources.openRawResource(R.raw.my_image)
                                while (true) {
                                    val data = imageInputStream.read()
                                    if (data == -1) break
                                    fileOutputStream.write(data)
                                }
                                imageInputStream.close()
                                fileOutputStream.close()
                            }
                        }
                    }
                    values.clear()
                    values.put(MediaStore.Images.Media.IS_PENDING, 0)
                    contentResolver.update(uri, values, null, null)
                }
            }
        }
    }

    private fun initBtStorageAccessFramework(binding: FragmentStorageBinding) {
        val contentResolver = requireActivity().contentResolver
        val SAFLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                activityResult.data?.let { data ->
                    val uri = data.data!!
                    val outputStream = contentResolver.openOutputStream(uri)
                    outputStream?.let {
                        val inputStream = resources.openRawResource(R.raw.my_image)
                        while (true) {
                            val data = inputStream.read()
                            if (data == -1) break
                            it.write(data)
                        }
                        inputStream.close()
                        it.close()
                    }
                }
            }
        }

        val writeIntent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
            putExtra(Intent.EXTRA_TITLE, "MonsterInc.jpg")
        }

        binding.btStorageAccessFramework.setOnClickListener {
            SAFLauncher.launch(writeIntent)
        }
    }
}