package com.example.studyandroid.view.MotionLayout

import com.google.gson.annotations.SerializedName

data class FragmentMotionLayoutRecyclerViewItemResponse(
    @SerializedName("heroes") val data: List<FragmentMotionLayoutRecyclerViewItem>? = null,
    val status: String = ""
)
