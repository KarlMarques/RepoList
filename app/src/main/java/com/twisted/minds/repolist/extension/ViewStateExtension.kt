package com.twisted.minds.repolist.extension

import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import com.twisted.minds.repolist.utils.FunctionParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal suspend fun <T> Flow<T>.runWithViewState(
    viewState: MutableStateFlow<ViewState<T>?>,
    block: FunctionParam<T>
) =
    onStart {
        viewState.value = ViewState.OnLoading
    }.catch {
        viewState.value = ViewState.OnError(
            when {
                it.isConnectionError() -> ErrorType.NetworkError
                it.isNetworkError() -> ErrorType.ServerError
                else -> ErrorType.GenericError
            }
        )
    }.collect {
        block.invoke(it)
    }

internal suspend fun <T> Flow<T>.collectViewState(viewState: MutableStateFlow<ViewState<T>?>) =
    onStart {
        viewState.value = ViewState.OnLoading
    }.catch {
        viewState.value = ViewState.OnError(
            when {
                it.isConnectionError() -> ErrorType.NetworkError
                it.isNetworkError() -> ErrorType.ServerError
                else -> ErrorType.GenericError
            }
        )
    }.collect {
        viewState.value = ViewState.OnSuccess(it)
    }

internal suspend fun <T> StateFlow<ViewState<T>?>?.collectIfNotNull(action: FunctionParam<ViewState<T>>) {
    this?.collect {
        it?.let(action::invoke)
    }
}

internal fun Throwable.isConnectionError() =
    this is UnknownHostException || cause is UnknownHostException ||
            this is SocketTimeoutException || cause is SocketTimeoutException

internal fun Throwable.isNetworkError() =
    this is ConnectException || cause is ConnectException
