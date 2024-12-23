package com.twisted.minds.repolist.ui.screens.pulllist

import androidx.lifecycle.viewModelScope
import com.twisted.minds.repolist.data.model.vo.PullVO
import com.twisted.minds.repolist.data.usecase.GetPullListUseCase
import com.twisted.minds.repolist.extension.collectViewState
import com.twisted.minds.repolist.ui.base.BaseViewModel
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class PullListViewModel(
    private val getPullListUseCase: GetPullListUseCase
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ViewState<List<PullVO>>?>(null)
    val viewState = _viewState.asStateFlow()

    fun getPullList(
        author: String,
        repo: String,
        isForce: Boolean = false
    ) {
        if (_viewState.value == null || isForce) {
            viewModelScope;launch {
                getPullListUseCase(
                    author = author,
                    repo = repo,
                ).collectViewState(_viewState)
            }
        }
    }

    fun clearViewState() {
        _viewState.value = null
    }
}
