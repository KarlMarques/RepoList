package com.twisted.minds.repolist.data.repository

import com.twisted.minds.repolist.data.model.dto.RepoListDTO
import kotlinx.coroutines.flow.Flow

internal interface RepoListRepository {

    fun getRepo(
        query: String,
        sort: String,
        page: Int
    ): Flow<RepoListDTO>
}
