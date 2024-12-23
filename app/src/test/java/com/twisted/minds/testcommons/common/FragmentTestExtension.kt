package com.twisted.minds.testcommons.common

import androidx.fragment.app.Fragment

private var isNavControllerMocked: Boolean = false

internal fun Fragment.clearLifecycleOwnerObservers() {
    if (isNavControllerMocked) {
        viewLifecycleOwnerLiveData.removeObservers(this)
        isNavControllerMocked = false
    }
}
