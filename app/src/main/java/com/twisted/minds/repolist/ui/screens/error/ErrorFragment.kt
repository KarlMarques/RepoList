package com.twisted.minds.repolist.ui.screens.error

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.twisted.minds.repolist.databinding.ErrorFragmentBinding
import com.twisted.minds.repolist.di.provideActivityViewModel
import com.twisted.minds.repolist.ui.activity.MainViewModel
import com.twisted.minds.repolist.ui.base.BaseFragment
import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.getErrorTextRes

internal class ErrorFragment : BaseFragment<ErrorFragmentBinding>() {

    private val activityViewModel: MainViewModel by activityViewModels {
        provideActivityViewModel()
    }

    private val errorType by lazy {
        activityViewModel.errorType
    }

    override fun onResume() {
        super.onResume()
        loadContent()
        setupListeners()
    }

    private fun loadContent() {
        var textRes = ErrorType.GenericError.getErrorTextRes()

        errorType?.let {
            textRes = it.getErrorTextRes()
        }

        binding.apply {
            errorHeaderTv.text = getString(textRes.first)
            errorCodeTv.text = getString(textRes.second)
            errorBodyTv.text = getString(textRes.third)
        }
    }

    private fun setupListeners() {
        binding.errorRetryBt.setOnClickListener {
            navigation.popBackStack(requireActivity())
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ErrorFragmentBinding.inflate(
        inflater,
        container,
        false
    )

    override fun setupCollectors() {
        // Do nothing
    }
}
