package com.twisted.minds.testcommons.common

import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.ConnectException
import java.net.SocketTimeoutException

@Suppress("TooGenericExceptionThrown")
internal class FlowResponseMock<T>(
    private val data: T,
    private val flow: () -> Flow<T>
) {

    fun mockSuccess() = every { flow.invoke() } returns flowOf(data)

    fun mockDelay() = every { flow.invoke() } returns flow { delay(2000) }

    fun mockGenericError() =
        coEvery { flow.invoke() } returns flow { throw Exception() }
}
