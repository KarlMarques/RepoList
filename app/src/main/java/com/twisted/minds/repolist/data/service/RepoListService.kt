package com.twisted.minds.repolist.data.service

import com.twisted.minds.repolist.data.model.dto.RepoListDTO
import kotlinx.coroutines.flow.Flow

internal interface RepoListService {

    fun getRepo(
        query: String,
        sort: String,
        page: Int
    ): Flow<RepoListDTO>
}
