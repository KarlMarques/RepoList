package com.twisted.minds.repolist.ui.screens.repolist

import androidx.lifecycle.viewModelScope
import com.twisted.minds.repolist.data.model.vo.RepoVO
import com.twisted.minds.repolist.data.usecase.GetRepoListUseCase
import com.twisted.minds.repolist.extension.runWithViewState
import com.twisted.minds.repolist.ui.base.BaseViewModel
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class RepoListViewModel(
    private val getRepoListUseCase: GetRepoListUseCase
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ViewState<List<RepoVO>>?>(null)
    val viewState = _viewState.asStateFlow()

    private var repoList = mutableListOf<RepoVO>()

    fun getRepoList(
        page: Int,
        isForce: Boolean = false
    ) {
        if (_viewState.value == null || isForce) {
            viewModelScope;launch {
                getRepoListUseCase(
                    query = REPO_LIST_QUERY,
                    sort = REPO_LIST_SORT,
                    page = page
                ).runWithViewState(
                    viewState = _viewState,
                    block = { newPage ->
                        onNewPageLoaded(
                            page,
                            newPage
                        )
                    }
                )
            }
        }
    }

    private fun onNewPageLoaded(
        page: Int,
        newPage: List<RepoVO>
    ) {
        if (page == INITIAL_PAGE) {
            repoList.clear()
        }
        repoList.addAll(newPage)

        _viewState.value = ViewState.OnSuccess(repoList)
    }

    fun clearViewState() {
        _viewState.value = null
    }

    companion object {
        private const val REPO_LIST_QUERY = "language:Java"
        private const val REPO_LIST_SORT = "stars"
        private const val INITIAL_PAGE = 0
    }
}
