package com.twisted.minds.repolist.ui.screens.repolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.twisted.minds.repolist.data.model.vo.RepoVO
import com.twisted.minds.repolist.data.usecase.GetRepoListUseCase
import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import com.twisted.minds.testcommons.common.MainDispatcherRule
import com.twisted.minds.testcommons.common.FlowResponseMock
import com.twisted.minds.testcommons.mock.mockRepoVOList
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
internal class RepoListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcher = MainDispatcherRule()

    private val useCase: GetRepoListUseCase = mockk(relaxed = true)
    private val repoListVO = mockRepoVOList(30)
    private val query = "language:Java"
    private val sort = "stars"
    private val page = 0

    private val mockFlow = FlowResponseMock(repoListVO) {
        useCase(query, sort, page)
    }
    private var viewModel: RepoListViewModel? = null

    @Before
    fun setUp() {
        viewModel = RepoListViewModel(useCase)
    }

    @Test
    fun givenLoadingState_whenGetRepoListCalled_thenReturnLoading() {
        runTest {
            // Arrange
            val values = mutableListOf<ViewState<List<RepoVO>>?>()
            val collectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel?.viewState?.toList(values)
            }
            mockFlow.mockDelay()

            // Act
            viewModel?.getRepoList(page)

            // Assert
            verify { useCase(query, sort, page) }
            assert(values.lastOrNull() is ViewState.OnLoading)

            collectorJob.cancel()
        }
    }

    @Test
    fun givenSuccessState_whenGetRepoListCalled_thenReturnSuccess() {
        runTest {
            // Arrange
            val values = mutableListOf<ViewState<List<RepoVO>>?>()
            val collectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel?.viewState?.toList(values)
            }
            mockFlow.mockSuccess()

            // Act
            viewModel?.getRepoList(page)

            // Assert
            verify { useCase(query, sort, page) }
            assert(ViewState.OnSuccess(repoListVO) == values.lastOrNull())

            collectorJob.cancel()
        }
    }

    @Test
    fun givenGenericErrorState_whenGetRepoListCalled_thenReturnGenericError() {
        runTest {
            // Arrange
            val values = mutableListOf<ViewState<List<RepoVO>>?>()
            val collectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel?.viewState?.toList(values)
            }
            mockFlow.mockGenericError()

            // Act
            viewModel?.getRepoList(page)

            // Assert
            val error = values.lastOrNull() as? ViewState.OnError
            verify { useCase(query, sort, page) }
            assert(error != null)
            assert(error?.errorType == ErrorType.GenericError)

            collectorJob.cancel()
        }
    }
}
