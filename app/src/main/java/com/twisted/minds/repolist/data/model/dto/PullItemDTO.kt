package com.twisted.minds.repolist.data.model.dto

import com.google.gson.annotations.SerializedName

data class PullItemDTO(
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val date: String,
    @SerializedName("body") val body: String?,
    @SerializedName("user") val user: UserDTO,
)
