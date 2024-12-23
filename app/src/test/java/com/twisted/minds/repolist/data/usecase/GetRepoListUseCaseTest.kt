package com.twisted.minds.repolist.data.usecase

import com.twisted.minds.repolist.data.mapper.toRepoVOList
import com.twisted.minds.repolist.data.repository.RepoListRepository
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
internal class GetRepoListUseCaseTest {

    private val repository: RepoListRepository = mockk(relaxed = true)

    private val useCase by lazy {
        GetRepoListUseCaseImpl(repository)
    }

    @Test
    fun givenGetRepoList_thenReturnPullListVOFlow() {
        runTest {
            // Arrange
            val repoListDTOMock = flowOf(mockRepoListDTO(30))
            every { repository.getRepo(anyString(), anyString(), 0) } returns repoListDTOMock

            // Act
            val repoList = useCase.invoke(anyString(), anyString(), 0)

            // Assert
            verify { repository.getRepo(anyString(), anyString(), 0) }
            assert(repoList.last() == repoListDTOMock.last().toRepoVOList())
        }
    }
}
