package com.twisted.minds.repolist.data.api

import com.twisted.minds.repolist.utils.createRetrofitApi

internal object RepoListApiImpl {

    @Volatile
    private var INSTANCE: RepoListApi? = null

    fun getInstance() = INSTANCE ?: synchronized(this) {
        val newInstance = createRetrofitApi(
            RepoListApi::class.java,
            BASE_URL
        )
        INSTANCE = newInstance
        newInstance
    }

    fun clearInstance() {
        INSTANCE = null
    }

    private const val BASE_URL = "https://api.github.com"
}
