package com.twisted.minds.repolist.data.repository

import com.twisted.minds.repolist.data.datasource.PullListRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

internal class PullListRepositoryImpl private constructor(
    private val repoListRemoteDataSource: PullListRemoteDataSource,
    private val coroutineContext: CoroutineContext
) : PullListRepository {

    override fun getPull(
        author: String,
        repo: String,
    ) = repoListRemoteDataSource.getPull(
        author,
        repo,
    ).flowOn(coroutineContext)

    companion object {

        @Volatile
        private var INSTANCE: PullListRepository? = null

        fun getInstance(
            remoteDataSource: PullListRemoteDataSource,
            coroutineContext: CoroutineContext = Dispatchers.IO
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: PullListRepositoryImpl(remoteDataSource, coroutineContext).also {
                INSTANCE = it
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
