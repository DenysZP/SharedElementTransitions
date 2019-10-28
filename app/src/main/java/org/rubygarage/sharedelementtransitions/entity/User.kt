package org.rubygarage.sharedelementtransitions.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: Int, val name: String, val details: String, val avatarUrl: String) :
    Parcelable