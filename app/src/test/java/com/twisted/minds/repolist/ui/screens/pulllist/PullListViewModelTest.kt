package com.twisted.minds.repolist.ui.screens.pulllist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.twisted.minds.repolist.data.model.vo.PullVO
import com.twisted.minds.repolist.data.usecase.GetPullListUseCase
import com.twisted.minds.repolist.ui.common.viewstate.ErrorType
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import com.twisted.minds.testcommons.common.FlowResponseMock
import com.twisted.minds.testcommons.common.MainDispatcherRule
import com.twisted.minds.testcommons.mock.mockPullVOList
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
internal class PullListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcher = MainDispatcherRule()

    private val useCase: GetPullListUseCase = mockk(relaxed = true)
    private val pullListVO = mockPullVOList(30)
    private val owner = "owner"
    private val repo = "repo"

    private val mockFlow = FlowResponseMock(pullListVO) {
        useCase(owner, repo)
    }
    private var viewModel: PullListViewModel? = null

    @Before
    fun setUp() {
        viewModel = PullListViewModel(useCase)
    }

    @Test
    fun givenLoadingState_whenGetPullListCalled_thenReturnLoading() {
        runTest {
            // Arrange
            val values = mutableListOf<ViewState<List<PullVO>>?>()
            val collectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel?.viewState?.toList(values)
            }
            mockFlow.mockDelay()

            // Act
            viewModel?.getPullList(owner, repo)

            // Assert
            verify { useCase(owner, repo) }
            assert(values.lastOrNull() is ViewState.OnLoading)

            collectorJob.cancel()
        }
    }

    @Test
    fun givenSuccessState_whenGetPullListCalled_thenReturnSuccess() {
        runTest {
            // Arrange
            val values = mutableListOf<ViewState<List<PullVO>>?>()
            val collectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel?.viewState?.toList(values)
            }
            mockFlow.mockSuccess()

            // Act
            viewModel?.getPullList(owner, repo)

            // Assert
            verify { useCase(owner, repo) }
            assert(ViewState.OnSuccess(pullListVO) == values.lastOrNull())

            collectorJob.cancel()
        }
    }

    @Test
    fun givenGenericErrorState_whenGetPullListCalled_thenReturnGenericError() {
        runTest {
            // Arrange
            val values = mutableListOf<ViewState<List<PullVO>>?>()
            val collectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel?.viewState?.toList(values)
            }
            mockFlow.mockGenericError()

            // Act
            viewModel?.getPullList(owner, repo)

            // Assert
            val error = values.lastOrNull() as? ViewState.OnError
            verify { useCase(owner, repo) }
            assert(error != null)
            assert(error?.errorType == ErrorType.GenericError)

            collectorJob.cancel()
        }
    }
}
