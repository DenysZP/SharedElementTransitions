package org.rubygarage.sharedelementtransitions.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val url: String, val width: Float, val height: Float): Parcelable