package com.twisted.minds.repolist.ui.screens.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twisted.minds.repolist.data.model.vo.RepoVO
import com.twisted.minds.repolist.databinding.RepoListFragmentBinding
import com.twisted.minds.repolist.di.provideActivityViewModel
import com.twisted.minds.repolist.di.provideRepoListViewModel
import com.twisted.minds.repolist.extension.collectIfNotNull
import com.twisted.minds.repolist.extension.launchAndRepeatOnLifecycle
import com.twisted.minds.repolist.extension.onBackPressed
import com.twisted.minds.repolist.ui.activity.MainViewModel
import com.twisted.minds.repolist.ui.base.BaseFragment
import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.ViewState

internal class RepoListFragment : BaseFragment<RepoListFragmentBinding>() {

    private val viewModel: RepoListViewModel by viewModels {
        provideRepoListViewModel()
    }

    private val activityViewModel: MainViewModel by activityViewModels {
        provideActivityViewModel()
    }

    private var adapter: RepoListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupListeners()
        loadInitialContent()
    }

    private fun setupAdapter() {
        adapter = RepoListAdapter(
            activity = requireActivity(),
            onClick = { repoVO ->
                openPullListScreen(repoVO)
            }
        )
        binding.repoListRv.adapter = adapter

        val endlessScrollListener = object : RepoListScrollListener(
            binding.repoListRv.layoutManager as LinearLayoutManager
        ) {
            override fun loadNextPage(
                page: Int,
                totalItemsCount: Int,
                view: RecyclerView?
            ) {
                viewModel.getRepoList(page)
            }
        }
        endlessScrollListener.setAdapter(adapter)
        binding.repoListRv.addOnScrollListener(endlessScrollListener)
    }

    private fun loadInitialContent() {
        viewModel.getRepoList(
            page = INITIAL_PAGE
        )
    }

    private fun setupListeners() {
        binding.repoListSrl.setOnRefreshListener {
            loadInitialContent()
        }
    }

    private fun displayLoading(loading: Boolean) {
        binding.apply {
            repoListRv.isVisible = !loading
            repoListSrl.isRefreshing = loading
        }

        if (loading) {
            removeOnBackPressedCallback()
        } else {
            onBackPressed {
                navigation.popBackStack(requireActivity())
            }
        }
    }

    private fun openPullListScreen(
        repoVO: RepoVO
    ) {
        activityViewModel.repoVO = repoVO
        navigation.navigateFromRepoListToPullList()
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = RepoListFragmentBinding.inflate(
        inflater,
        container,
        false
    )

    override fun setupCollectors() {
        launchAndRepeatOnLifecycle {
            viewModel.viewState.collectIfNotNull(::handleViewState)
        }
    }

    private fun handleViewState(viewState: ViewState<List<RepoVO>>) =
        when (viewState) {
            is ViewState.OnLoading -> onLoading()
            is ViewState.OnSuccess -> onSuccess(viewState.data)
            is ViewState.OnError -> onError(viewState.errorType)
        }

    private fun onLoading() {
        displayLoading(true)
    }

    private fun onSuccess(listRepoVO: List<RepoVO>) {
        displayLoading(false)

        binding.repoListRv.post {
            adapter?.list = listRepoVO
        }
        viewModel.clearViewState()
    }

    private fun onError(errorType: ErrorType) {
        displayLoading(false)

        activityViewModel.errorType = errorType
        navigation.navigateFromRepoListToError()
        viewModel.clearViewState()
    }

    companion object {
        private const val INITIAL_PAGE = 0
    }
}
