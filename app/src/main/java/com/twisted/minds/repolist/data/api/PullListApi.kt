package com.twisted.minds.repolist.data.api

import com.twisted.minds.repolist.data.model.dto.PullItemDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PullListApi {
    @GET("/repos/{author}/{repo}/pulls")
    suspend fun getPull(
        @Path("author") author: String,
        @Path("repo") repo: String,
    ): ArrayList<PullItemDTO>
}