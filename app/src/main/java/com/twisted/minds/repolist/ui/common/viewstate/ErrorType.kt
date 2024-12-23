package com.twisted.minds.repolist.ui.common.viewstate

import com.twisted.minds.repolist.R

internal sealed class ErrorType {
    object NetworkError: ErrorType()
    object ServerError : ErrorType()
    object GenericError : ErrorType()
}

internal fun ErrorType.getErrorTextRes(): Triple<Int, Int, Int> = when (this) {
    is ErrorType.NetworkError ->
        Triple(
            first = R.string.error_type_network_error_header,
            second = R.string.error_type_network_error_code,
            third = R.string.error_type_network_error_body
        )
    is ErrorType.ServerError ->
        Triple(
            first = R.string.error_type_server_error_header,
            second = R.string.error_type_server_error_code,
            third = R.string.error_type_server_error_body
        )
    is ErrorType.GenericError ->
        Triple(
            first = R.string.error_type_generic_error_header,
            second = R.string.error_type_generic_error_code,
            third = R.string.error_type_generic_error_body
        )
}
