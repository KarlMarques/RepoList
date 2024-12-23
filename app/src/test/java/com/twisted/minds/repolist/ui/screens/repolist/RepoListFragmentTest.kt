package com.twisted.minds.repolist.ui.screens.repolist

import com.twisted.minds.testcommons.base.BaseFragmentTest
import org.junit.Test

internal class RepoListFragmentTest :
    BaseFragmentTest<RepoListFragmentRobot>() {

    override val robot by lazy { RepoListFragmentRobot() }

    @Test
    fun givenLoadingState_thenContentIsDisplayed() {
        robot arrange {
            arrangeStateLoading()
        } assert {
            assertTitleIsDisplayed()
            assertLoadingIsDisplayed()
        }
    }
}
