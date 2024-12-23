package com.twisted.minds.repolist.data.repository

import com.twisted.minds.repolist.data.datasource.RepoListRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

internal class RepoListRepositoryImpl private constructor(
    private val repoListRemoteDataSource: RepoListRemoteDataSource,
    private val coroutineContext: CoroutineContext
) : RepoListRepository {

    override fun getRepo(
        query: String,
        sort: String,
        page: Int
    ) = repoListRemoteDataSource.getRepo(
        query,
        sort,
        page
    ).flowOn(coroutineContext)

    companion object {

        @Volatile
        private var INSTANCE: RepoListRepository? = null

        fun getInstance(
            remoteDataSource: RepoListRemoteDataSource,
            coroutineContext: CoroutineContext = Dispatchers.IO
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RepoListRepositoryImpl(remoteDataSource, coroutineContext).also {
                INSTANCE = it
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
