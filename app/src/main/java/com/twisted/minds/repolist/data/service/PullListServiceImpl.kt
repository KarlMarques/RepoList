package com.twisted.minds.repolist.data.service

import com.twisted.minds.repolist.data.api.PullListApi
import com.twisted.minds.repolist.data.model.dto.PullItemDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PullListServiceImpl private constructor(
    val pullListApi: PullListApi
) : PullListService {

    override fun getPull(
        author: String,
        repo: String,
    ): Flow<ArrayList<PullItemDTO>> = flow {
        emit(
            pullListApi.getPull(
                author,
                repo
            )
        )
    }

    companion object {

        @Volatile
        private var INSTANCE: PullListService? = null

        fun getInstance(
            pullListApi: PullListApi
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: PullListServiceImpl(pullListApi).also {
                INSTANCE = it
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
