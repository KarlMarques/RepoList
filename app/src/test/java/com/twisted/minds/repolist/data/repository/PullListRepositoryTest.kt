package com.twisted.minds.repolist.data.repository

import com.twisted.minds.repolist.data.datasource.PullListRemoteDataSource
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
internal class PullListRepositoryTest {

    private val remoteDataSource: PullListRemoteDataSource = mockk(relaxed = true)

    private val repository by lazy {
        PullListRepositoryImpl.getInstance(remoteDataSource)
    }

    @Test
    fun givenGetPullList_thenReturnPullListDTOFlow() {
        runTest {
            // Arrange
            val pullListDTOMock = flowOf(mockPullListDTO(30))
            every { remoteDataSource.getPull(anyString(), anyString()) } returns pullListDTOMock

            // Act
            val pullList = repository.getPull(anyString(), anyString())

            // Assert
            verify { remoteDataSource.getPull(anyString(), anyString()) }
            assert(pullList.last() == pullListDTOMock.last())
        }
    }
}
