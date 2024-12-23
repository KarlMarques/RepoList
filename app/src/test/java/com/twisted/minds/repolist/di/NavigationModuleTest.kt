package com.twisted.minds.repolist.di

import androidx.fragment.app.Fragment
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

internal class NavigationModuleTest {

    @Test
    fun givenProvideNavigation_thenReturnNavigation() {
        // Arrange
        val fragment = mockk<Fragment>(relaxed = true)

        // Act
        val navigation = provideNavigation(fragment)

        // Assert
        assertNotNull(navigation)
    }
}
