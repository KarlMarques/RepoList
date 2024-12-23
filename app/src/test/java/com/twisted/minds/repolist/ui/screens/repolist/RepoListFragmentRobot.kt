package com.twisted.minds.repolist.ui.screens.repolist

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.twisted.minds.repolist.di.provideActivityViewModel
import com.twisted.minds.repolist.di.provideNavigation
import com.twisted.minds.repolist.navigation.MainNavigation
import com.twisted.minds.repolist.ui.activity.MainViewModel
import com.twisted.minds.repolist.ui.screens.repolist.RepoListFragmentRobot.Act
import com.twisted.minds.repolist.ui.screens.repolist.RepoListFragmentRobot.Arrange
import com.twisted.minds.repolist.ui.screens.repolist.RepoListFragmentRobot.Assert
import com.twisted.minds.repolist.utils.initializerViewModelFactory
import com.twisted.minds.testcommons.base.BaseFragmentRobotTest
import com.twisted.minds.repolist.R
import com.twisted.minds.repolist.data.model.vo.RepoVO
import com.twisted.minds.repolist.di.provideRepoListViewModel
import com.twisted.minds.repolist.ui.common.viewstate.ViewState
import com.twisted.minds.testcommons.common.ViewStateMock
import com.twisted.minds.testcommons.mock.mockRepoVOList
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow

internal class RepoListFragmentRobot :
    BaseFragmentRobotTest<RepoListFragmentRobot, RepoListFragment, Arrange, Act, Assert>() {

    override val arrange by lazy { Arrange() }
    override val act by lazy { Act() }
    override val assert by lazy { Assert() }
    override val fragment: RepoListFragment by lazy { RepoListFragment() }

    private val activityViewModel = mockk<MainViewModel>(relaxed = true)
    private val viewModel = mockk<RepoListViewModel>(relaxed = true)
    private val viewStateMock by lazy {
        MutableStateFlow<ViewState<List<RepoVO>>?>(null)
    }

    private val navigation: MainNavigation = mockk(relaxed = true)

    inner class Arrange {

        private val mockViewState = ViewStateMock(mockRepoVOList(30), viewStateMock)

        init {
            mockkStatic(::provideActivityViewModel)
            every { provideActivityViewModel() } returns initializerViewModelFactory { activityViewModel }
            mockkStatic(::provideRepoListViewModel)
            every { provideRepoListViewModel() } returns initializerViewModelFactory { viewModel }
            every { viewModel.viewState } returns viewStateMock
        }

        private fun arrangeLaunch() {
            scenario = launchFragmentInContainer {
                fragment
            }
        }

        private fun arrangeMockLoading() {
            mockViewState.loading()
        }

        private fun arrangeMockSuccess() {
            mockViewState.success(mockRepoVOList(30))
        }

        private fun arrangeMockError() {
            mockViewState.error()
        }

        private fun arrangeMockNavigation() {
            mockkStatic(::provideNavigation)
            every { provideNavigation(fragment) } returns navigation
        }

        fun arrangeStateLoading() {
            arrangeMockLoading()
            arrangeMockNavigation()
            arrangeLaunch()
        }

        fun arrangeStateSuccess() {
            arrangeMockSuccess()
            arrangeMockNavigation()
            arrangeLaunch()
        }

        fun arrangeStateError() {
            arrangeMockError()
            arrangeMockNavigation()
            arrangeLaunch()
        }
    }

    inner class Act {
        fun actPressBackKey() =
            scenario?.onFragment {
                it.requireActivity().onBackPressed()
            }
    }

    inner class Assert {
        fun assertNavigationPopBackStack() =
            verify { navigation.popBackStack(fragment.requireActivity()) }

        fun assertPullListScreenIsOpened() =
            verify { navigation.navigateFromRepoListToPullList() }

        fun assertErrorScreenIsOpened() =
            verify { navigation.navigateFromRepoListToError() }

        fun assertTitleIsDisplayed(): ViewInteraction =
            onView(ViewMatchers.withText(R.string.repo_list_title))
                .check(matches(isDisplayed()))

        fun assertLoadingIsDisplayed(): ViewInteraction =
            onView(withId(R.id.repo_list_srl))
                .check(matches(isDisplayed()))
    }
}
