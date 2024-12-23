package com.twisted.minds.repolist.di

import com.twisted.minds.repolist.data.datasource.PullListRemoteDataSource
import com.twisted.minds.repolist.data.datasource.PullListRemoteDataSourceImpl
import com.twisted.minds.repolist.data.datasource.RepoListRemoteDataSource
import com.twisted.minds.repolist.data.datasource.RepoListRemoteDataSourceImpl

internal fun bindRepoListRemoteDataSource(): RepoListRemoteDataSource =
    RepoListRemoteDataSourceImpl.getInstance(bindRepoListService())

internal fun bindPullListRemoteDataSource(): PullListRemoteDataSource =
    PullListRemoteDataSourceImpl.getInstance(bindPullListService())

internal fun clearRemoteDataSourceInstances() {
    RepoListRemoteDataSourceImpl.clearInstance()
    PullListRemoteDataSourceImpl.clearInstance()
}
