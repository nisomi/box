package com.example.box.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class Option(
    val boxCount: Int
    ) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT = Option(boxCount = 3)
    }
}