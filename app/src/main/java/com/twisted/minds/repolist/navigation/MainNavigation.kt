package com.twisted.minds.repolist.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.twisted.minds.repolist.R

internal class MainNavigation(
    fragment: Fragment
) {
    private val navController: NavController by lazy { fragment.findNavController() }

    fun navigateFromRepoListToPullList() {
        navController.navigate(R.id.action_from_repo_list_to_pull_list)
    }

    fun navigateFromRepoListToError() {
        navController.navigate(R.id.action_from_repo_list_to_error)
    }

    fun navigateFromPullListToError() {
        navController.navigate(R.id.action_from_pull_list_to_error)
    }

    fun popBackStack(activity: Activity) {
        if (navController.previousBackStackEntry == null) {
            activity.finish()
        } else {
            navController.popBackStack()
        }
    }

    fun finishFlow(activity: Activity) {
        activity.finish()
    }
}
