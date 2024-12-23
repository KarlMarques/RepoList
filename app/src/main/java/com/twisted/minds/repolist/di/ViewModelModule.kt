package com.twisted.minds.repolist.di

import com.twisted.minds.repolist.ui.activity.MainViewModel
import com.twisted.minds.repolist.ui.screens.pulllist.PullListViewModel
import com.twisted.minds.repolist.ui.screens.repolist.RepoListViewModel
import com.twisted.minds.repolist.utils.initializerViewModelFactory

internal fun provideActivityViewModel() =
    initializerViewModelFactory { MainViewModel() }

internal fun provideRepoListViewModel() =
    initializerViewModelFactory { RepoListViewModel(bindGetRepoListUseCase()) }

internal fun providePullListViewModel() =
    initializerViewModelFactory { PullListViewModel(bindGetPullListUseCase()) }
