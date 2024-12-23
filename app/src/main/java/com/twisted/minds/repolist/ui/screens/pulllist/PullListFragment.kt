package com.twisted.minds.repolist.ui.screens.pulllist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.twisted.minds.repolist.R
import com.twisted.minds.repolist.data.model.vo.PullVO
import com.twisted.minds.repolist.databinding.PullListFragmentBinding
import com.twisted.minds.repolist.di.provideActivityViewModel
import com.twisted.minds.repolist.di.providePullListViewModel
import com.twisted.minds.repolist.extension.collectIfNotNull
import com.twisted.minds.repolist.extension.launchAndRepeatOnLifecycle
import com.twisted.minds.repolist.extension.onBackPressed
import com.twisted.minds.repolist.ui.activity.MainViewModel
import com.twisted.minds.repolist.ui.base.BaseFragment
import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.ViewState

internal class PullListFragment : BaseFragment<PullListFragmentBinding>() {

    private val viewModel: PullListViewModel by viewModels {
        providePullListViewModel()
    }

    private val activityViewModel: MainViewModel by activityViewModels {
        provideActivityViewModel()
    }

    private val repoVO by lazy {
        activityViewModel.repoVO
    }

    override fun onResume() {
        super.onResume()
        loadContent()
        setupListeners()
    }

    private fun loadContent() {
        viewModel.getPullList(
            author = repoVO?.authorName.orEmpty(),
            repo = repoVO?.name.orEmpty()
        )
    }

    private fun setupListeners() {
        binding.pullListSrl.setOnRefreshListener {
            loadContent()
        }
    }

    private fun displayLoading(loading: Boolean) {
        binding.apply {
            pullListRv.isVisible = !loading
            pullListSrl.isRefreshing = loading
        }

        if (loading) {
            removeOnBackPressedCallback()
        } else {
            onBackPressed {
                navigation.popBackStack(requireActivity())
            }
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = PullListFragmentBinding.inflate(
        inflater,
        container,
        false
    )

    override fun setupCollectors() {
        launchAndRepeatOnLifecycle {
            viewModel.viewState.collectIfNotNull(::handleViewState)
        }
    }

    private fun handleViewState(viewState: ViewState<List<PullVO>>) =
        when (viewState) {
            is ViewState.OnLoading -> onLoading()
            is ViewState.OnSuccess -> onSuccess(viewState.data)
            is ViewState.OnError -> onError(viewState.errorType)
        }

    private fun onLoading() {
        binding.apply {
            displayLoading(true)
        }
    }

    private fun onSuccess(listPullVO: List<PullVO>) {
        displayLoading(false)

        setupRecyclerView(listPullVO)
        viewModel.clearViewState()
    }

    private fun setupRecyclerView(listPullVO: List<PullVO>) {
        binding.apply {
            pullListRv.post {
                val adapter = PullListAdapter(
                    activity = requireActivity()
                )
                pullListRepoNameTv.text =
                    requireActivity().getString(
                        R.string.pull_list_title,
                        repoVO?.name
                    )
                pullListRv.adapter = adapter

                adapter.list = listPullVO
            }
        }
    }

    private fun onError(errorType: ErrorType) {
        displayLoading(false)

        viewModel.clearViewState()
        activityViewModel.errorType = errorType
        navigation.navigateFromPullListToError()
    }
}
