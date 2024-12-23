package com.twisted.minds.repolist.ui.activity

import com.twisted.minds.repolist.R
import com.twisted.minds.repolist.di.clearApiInstances
import com.twisted.minds.repolist.di.clearRemoteDataSourceInstances
import com.twisted.minds.repolist.di.clearRepositoriesInstances
import com.twisted.minds.repolist.di.clearServiceInstances
import com.twisted.minds.repolist.ui.base.BaseActivity

internal class MainActivity :
    BaseActivity(R.layout.main_activity) {

    var onBackPressed: (() -> Unit)? = null

    override fun initView() {
        // Do nothing
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBackPressed?.invoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearRepositoriesInstances()
        clearRemoteDataSourceInstances()
        clearServiceInstances()
        clearApiInstances()
    }
}
