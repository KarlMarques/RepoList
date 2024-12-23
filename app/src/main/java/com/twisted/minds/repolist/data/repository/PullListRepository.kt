package com.twisted.minds.repolist.data.repository

import com.twisted.minds.repolist.data.model.dto.PullItemDTO
import kotlinx.coroutines.flow.Flow

internal interface PullListRepository {

    fun getPull(
        author: String,
        repo: String,
    ): Flow<ArrayList<PullItemDTO>>
}
