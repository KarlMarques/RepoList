package com.twisted.minds.repolist.di

import com.twisted.minds.repolist.data.usecase.GetPullListUseCase
import com.twisted.minds.repolist.data.usecase.GetPullListUseCaseImpl
import com.twisted.minds.repolist.data.usecase.GetRepoListUseCase
import com.twisted.minds.repolist.data.usecase.GetRepoListUseCaseImpl

internal fun bindGetRepoListUseCase(): GetRepoListUseCase =
    GetRepoListUseCaseImpl(bindRepoListRepository())

internal fun bindGetPullListUseCase(): GetPullListUseCase =
    GetPullListUseCaseImpl(bindPullListRepository())
