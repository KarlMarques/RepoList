package com.twisted.minds.testcommons.base

import android.app.Application
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], application = Application::class)
internal abstract class BaseFragmentTest<R : BaseFragmentRobotTest<*, *, *, *, *>> {

    protected abstract val robot: R

    @After
    open fun tearDown() {
        robot.tearDown()
    }
}
