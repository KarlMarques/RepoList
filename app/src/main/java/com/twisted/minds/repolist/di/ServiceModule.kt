package com.twisted.minds.repolist.di

import com.twisted.minds.repolist.data.service.PullListService
import com.twisted.minds.repolist.data.service.PullListServiceImpl
import com.twisted.minds.repolist.data.service.RepoListService
import com.twisted.minds.repolist.data.service.RepoListServiceImpl

internal fun bindRepoListService(): RepoListService =
    RepoListServiceImpl.getInstance(provideRepoListApi())

internal fun bindPullListService(): PullListService =
    PullListServiceImpl.getInstance(providePullListApi())

internal fun clearServiceInstances() {
    RepoListServiceImpl.clearInstance()
    PullListServiceImpl.clearInstance()
}
