package com.twisted.minds.repolist.data.model.dto

import com.google.gson.annotations.SerializedName

data class RepoItemDTO(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val stars: Double,
    @SerializedName("forks") val forks: Double,
    @SerializedName("owner") val owner: UserDTO,
)
