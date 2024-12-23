package com.twisted.minds.repolist.di

import com.twisted.minds.repolist.data.api.PullListApi
import com.twisted.minds.repolist.data.api.PullListApiImpl
import com.twisted.minds.repolist.data.api.RepoListApi
import com.twisted.minds.repolist.data.api.RepoListApiImpl

internal fun provideRepoListApi(): RepoListApi =
    RepoListApiImpl.getInstance()

internal fun providePullListApi(): PullListApi =
    PullListApiImpl.getInstance()

internal fun clearApiInstances() {
    RepoListApiImpl.clearInstance()
    PullListApiImpl.clearInstance()
}
