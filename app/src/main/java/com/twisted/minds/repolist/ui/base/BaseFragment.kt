package com.twisted.minds.repolist.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.twisted.minds.repolist.di.provideNavigation

internal abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    protected val binding get() = _binding!!
    protected val navigation by lazy { provideNavigation(this) }
    private var onBackPressedCallback: OnBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupCollectors()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    protected fun setOnBackPressed(block: (() -> Unit)? = null) {
        removeOnBackPressedCallback()
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                block?.invoke()
            }
        }.also {
            requireActivity().onBackPressedDispatcher.addCallback(this, it)
        }
    }

    protected fun removeOnBackPressedCallback() {
        onBackPressedCallback?.remove()
        onBackPressedCallback = null
    }
    protected open fun setupCollectors() {
        // Do nothing
    }

    protected fun finishFlow() {
        navigation.finishFlow(requireActivity())
    }

    protected abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}
