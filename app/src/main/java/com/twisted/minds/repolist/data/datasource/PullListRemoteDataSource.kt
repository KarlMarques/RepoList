package com.twisted.minds.repolist.data.datasource

import com.twisted.minds.repolist.data.model.dto.PullItemDTO
import kotlinx.coroutines.flow.Flow

internal interface PullListRemoteDataSource {

    fun getPull(
        author: String,
        repo: String,
    ): Flow<ArrayList<PullItemDTO>>
}
