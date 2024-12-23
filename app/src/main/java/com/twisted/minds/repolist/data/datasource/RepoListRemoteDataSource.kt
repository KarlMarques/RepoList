package com.twisted.minds.repolist.data.datasource

import com.twisted.minds.repolist.data.model.dto.RepoListDTO
import kotlinx.coroutines.flow.Flow

internal interface RepoListRemoteDataSource {

    fun getRepo(
        query: String,
        sort: String,
        page: Int
    ): Flow<RepoListDTO>
}
