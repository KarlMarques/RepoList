package com.twisted.minds.repolist.data.model.dto

import com.google.gson.annotations.SerializedName

data class RepoListDTO(
    @SerializedName("items") val items: ArrayList<RepoItemDTO>,
)
