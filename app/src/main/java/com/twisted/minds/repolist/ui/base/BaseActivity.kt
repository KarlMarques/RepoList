package com.twisted.minds.repolist.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

internal abstract class BaseActivity(private val contentView: Int) : AppCompatActivity() {

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        supportActionBar?.hide()
        this.initView()
    }

    protected fun findNavController(@IdRes id: Int) = findNavHostFragment(id).navController

    protected fun findNavHostFragment(@IdRes id: Int) =
        supportFragmentManager.findFragmentById(id) as NavHostFragment
}
