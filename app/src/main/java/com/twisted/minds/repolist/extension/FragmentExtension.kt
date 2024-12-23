package com.twisted.minds.repolist.extension

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.twisted.minds.repolist.utils.FunctionDefault

internal fun Fragment.onBackPressed(block: FunctionDefault) {
    requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            block()
        }
    })
}
