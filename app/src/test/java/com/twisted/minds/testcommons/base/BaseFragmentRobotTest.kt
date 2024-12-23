package com.twisted.minds.testcommons.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import com.twisted.minds.testcommons.common.clearLifecycleOwnerObservers

internal abstract class BaseFragmentRobotTest<R : BaseFragmentRobotTest<R, F, AR, AC, AS>, F: Fragment, AR, AC, AS> {

    protected var scenario: FragmentScenario<F>? = null
    protected abstract val fragment: F
    protected abstract val arrange: AR
    protected abstract val act: AC
    protected abstract val assert: AS

    private fun clearFragmentObservers() {
        scenario?.onFragment {
            it.clearLifecycleOwnerObservers()
        }
    }

    fun tearDown() {
        clearFragmentObservers()
        scenario = null
    }

    @Suppress("UNCHECKED_CAST")
    protected fun self() = this@BaseFragmentRobotTest as R

    inline infix fun arrange(func: AR.() -> Unit): R {
        arrange.run(func)
        return self()
    }

    inline infix fun act(func: AC.() -> Unit): R {
        act.run(func)
        return self()
    }

    inline infix fun assert(func: AS.() -> Unit) {
        assert.apply(func)
    }
}
