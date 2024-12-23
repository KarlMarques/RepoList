package com.twisted.minds.repolist.data.repository

import com.twisted.minds.repolist.data.datasource.RepoListRemoteDataSource
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
internal class RepoListRepositoryTest {

    private val remoteDataSource: RepoListRemoteDataSource = mockk(relaxed = true)

    private val repository by lazy {
        RepoListRepositoryImpl.getInstance(remoteDataSource)
    }

    @Test
    fun givenGetRepoList_thenReturnRepoListVOFlow() {
        runTest {
            // Arrange
            val repoListDTOMock = flowOf(mockRepoListDTO(30))
            every { remoteDataSource.getRepo(anyString(), anyString(), 0) } returns repoListDTOMock

            // Act
            val repoList = repository.getRepo(anyString(), anyString(), 0)

            // Assert
            verify { remoteDataSource.getRepo(anyString(), anyString(), 0) }
            assert(repoList.last() == repoListDTOMock.last())
        }
    }
}
