package com.twisted.minds.testcommons.common

import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import kotlinx.coroutines.flow.MutableStateFlow

internal class ViewStateMock<T>(
    private val data: T,
    private val mutableStateFlow: MutableStateFlow<ViewState<T>?>
) {

    fun loading() {
        mutableStateFlow.value = ViewState.OnLoading
    }

    fun success(data: T? = null) {
        mutableStateFlow.value = ViewState.OnSuccess(data ?: this.data)
    }

    fun error() {
        mutableStateFlow.value = ViewState.OnError(ErrorType.GenericError)
    }
}
