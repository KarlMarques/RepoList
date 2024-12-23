package com.twisted.minds.repolist.data.api

import com.twisted.minds.repolist.utils.createRetrofitApi

internal object PullListApiImpl {

    @Volatile
    private var INSTANCE: PullListApi? = null

    fun getInstance() = INSTANCE ?: synchronized(this) {
        val newInstance = createRetrofitApi(
            PullListApi::class.java,
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
