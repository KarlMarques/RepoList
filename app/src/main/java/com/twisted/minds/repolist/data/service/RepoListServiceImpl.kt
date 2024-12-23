package com.twisted.minds.repolist.data.service

import com.twisted.minds.repolist.data.model.dto.RepoListDTO
import com.twisted.minds.repolist.data.api.RepoListApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RepoListServiceImpl private constructor(
    val repoListApi: RepoListApi
) : RepoListService {

    override fun getRepo(
        query: String,
        sort: String,
        page: Int
    ): Flow<RepoListDTO> = flow {
        emit(
            repoListApi.getRepo(
                query,
                sort,
                page
            )
        )
    }

    companion object {

        @Volatile
        private var INSTANCE: RepoListService? = null

        fun getInstance(
            repoListApi: RepoListApi
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RepoListServiceImpl(repoListApi).also {
                INSTANCE = it
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
