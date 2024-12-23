package com.twisted.minds.repolist.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

internal inline fun <reified  VM : ViewModel> initializerViewModelFactory(crossinline  block: () -> VM) =
    viewModelFactory {
        initializer { block() }
    }
