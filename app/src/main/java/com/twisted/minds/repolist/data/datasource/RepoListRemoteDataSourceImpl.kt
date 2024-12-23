package com.twisted.minds.repolist.data.datasource

import com.twisted.minds.repolist.data.service.RepoListService

internal class RepoListRemoteDataSourceImpl private constructor(
    private val service: RepoListService
) : RepoListRemoteDataSource {

    override fun getRepo(
        query: String,
        sort: String,
        page: Int
    ) = service.getRepo(
        query,
        sort,
        page
    )

    companion object {

        @Volatile
        private var INSTANCE: RepoListRemoteDataSource? = null

        fun getInstance(
            service: RepoListService
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RepoListRemoteDataSourceImpl(service).also {
                INSTANCE = it
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
