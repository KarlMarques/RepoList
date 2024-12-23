package com.twisted.minds.repolist.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("avatar_url") val userPicture: String,
    @SerializedName("login") val userName: String,
)
