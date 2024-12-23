package com.twisted.minds.repolist.data.api

import com.twisted.minds.repolist.data.model.dto.RepoListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoListApi {
    @GET("/search/repositories")
    suspend fun getRepo(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): RepoListDTO
}
