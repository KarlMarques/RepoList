package com.twisted.minds.repolist.data.datasource

import com.twisted.minds.repolist.data.service.PullListService

internal class PullListRemoteDataSourceImpl private constructor(
    private val service: PullListService
) : PullListRemoteDataSource {

    override fun getPull(
        author: String,
        repo: String,
    ) = service.getPull(
        author,
        repo
    )

    companion object {

        @Volatile
        private var INSTANCE: PullListRemoteDataSource? = null

        fun getInstance(
            service: PullListService
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: PullListRemoteDataSourceImpl(service).also {
                INSTANCE = it
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
