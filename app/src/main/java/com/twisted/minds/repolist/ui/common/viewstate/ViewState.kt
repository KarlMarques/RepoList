package com.twisted.minds.repolist.ui.common.viewstate

internal sealed class ViewState<out T> {
    data class OnSuccess<T>(val data: T) : ViewState<T>()
    object OnLoading : ViewState<Nothing>()
    data class OnError<T>(val errorType: ErrorType) : ViewState<T>()
}
