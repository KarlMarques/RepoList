package com.twisted.minds.repolist.data.datasource

import com.twisted.minds.repolist.data.service.RepoListService
import com.twisted.minds.testcommons.mock.mockRepoListDTO
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
internal class RepoListRemoteDataSourceTest {

    private val service: RepoListService = mockk(relaxed = true)

    private val remoteDataSource by lazy {
        RepoListRemoteDataSourceImpl.getInstance(service)
    }

    @Test
    fun givenGetRepoList_thenReturnRepoListVOFlow() {
        runTest {
            // Arrange
            val repoListDTOMock = flowOf(mockRepoListDTO(30))
            every { service.getRepo(anyString(), anyString(), 0) } returns repoListDTOMock

            // Act
            val repoList = remoteDataSource.getRepo(anyString(), anyString(), 0)

            // Assert
            verify { service.getRepo(anyString(), anyString(), 0) }
            assert(repoList.last() == repoListDTOMock.last())
        }
    }
}
