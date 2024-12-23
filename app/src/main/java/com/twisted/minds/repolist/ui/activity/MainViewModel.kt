package com.twisted.minds.repolist.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twisted.minds.repolist.data.model.vo.RepoVO
import com.twisted.minds.repolist.ui.common.viewstate.ErrorType

internal class MainViewModel : ViewModel() {

    private val _repoVO = MutableLiveData<RepoVO?>()
    var repoVO: RepoVO?
        get() = _repoVO.value
        set(value) {
            _repoVO.value = value
        }

    private val _errorType = MutableLiveData<ErrorType?>()
    var errorType: ErrorType?
        get() = _errorType.value
        set(value) {
            _errorType.value = value
        }
}
