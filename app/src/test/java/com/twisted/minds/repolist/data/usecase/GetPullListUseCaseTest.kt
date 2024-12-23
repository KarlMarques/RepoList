package com.twisted.minds.repolist.data.usecase

import com.twisted.minds.repolist.data.mapper.toPullVOList
import com.twisted.minds.repolist.data.repository.PullListRepository
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
internal class GetPullListUseCaseTest {

    private val repository: PullListRepository = mockk(relaxed = true)

    private val useCase by lazy {
        GetPullListUseCaseImpl(repository)
    }

    @Test
    fun givenGetPullList_thenReturnPullListVOFlow() {
        runTest {
            // Arrange
            val pullListDTOMock = flowOf(mockPullListDTO(30))
            every { repository.getPull(anyString(), anyString()) } returns pullListDTOMock

            // Act
            val pullList = useCase.invoke(anyString(), anyString())

            // Assert
            verify { repository.getPull(anyString(), anyString()) }
            assert(pullList.last() == pullListDTOMock.last().toPullVOList())
        }
    }
}
