package com.twisted.minds.repolist.di

import com.twisted.minds.repolist.data.repository.PullListRepository
import com.twisted.minds.repolist.data.repository.PullListRepositoryImpl
import com.twisted.minds.repolist.data.repository.RepoListRepository
import com.twisted.minds.repolist.data.repository.RepoListRepositoryImpl

internal fun bindRepoListRepository(): RepoListRepository =
    RepoListRepositoryImpl.getInstance(bindRepoListRemoteDataSource())

internal fun bindPullListRepository(): PullListRepository =
    PullListRepositoryImpl.getInstance(bindPullListRemoteDataSource())

internal fun clearRepositoriesInstances() {
    RepoListRepositoryImpl.clearInstance()
    PullListRepositoryImpl.clearInstance()
}
