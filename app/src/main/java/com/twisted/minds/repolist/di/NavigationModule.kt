package com.twisted.minds.repolist.di

import androidx.fragment.app.Fragment
import com.twisted.minds.repolist.navigation.MainNavigation

internal fun provideNavigation(fragment: Fragment) =
    MainNavigation(fragment)
