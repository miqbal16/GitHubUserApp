package com.muhammadiqbal.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataFollower(
        var username: String? = null,
        var name: String? = null,
        var avatar: String? = null,
        var company: String? = null,
        var location: String? = null,
        var followers: Int = 0,
        var following: Int = 0,
        var repository: Int = 0
) : Parcelable
