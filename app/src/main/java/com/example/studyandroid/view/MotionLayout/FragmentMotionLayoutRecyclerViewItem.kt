package com.example.studyandroid.view.MotionLayout

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FragmentMotionLayoutRecyclerViewItem(
    @SerializedName("name") val name: String,
    @SerializedName("src") val src: String
) : Parcelable
