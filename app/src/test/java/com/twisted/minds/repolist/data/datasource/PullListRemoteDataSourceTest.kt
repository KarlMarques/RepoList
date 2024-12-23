package com.twisted.minds.repolist.data.datasource

import com.twisted.minds.repolist.data.service.PullListService
import com.twisted.minds.testcommons.mock.mockPullListDTO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
internal class PullListRemoteDataSourceTest {

    private val service: PullListService = mockk(relaxed = true)

    private val remoteDataSource by lazy {
        PullListRemoteDataSourceImpl.getInstance(service)
    }

    @Test
    fun givenGetPullList_thenReturnPullListDTOFlow() {
        runTest {
            // Arrange
            val pullListDTOMock = flowOf(mockPullListDTO(30))
            every { service.getPull(anyString(), anyString()) } returns pullListDTOMock

            // Act
            val pullList = remoteDataSource.getPull(anyString(), anyString())

            // Assert
            verify { service.getPull(anyString(), anyString()) }
            assert(pullList.last() == pullListDTOMock.last())
        }
    }
}
