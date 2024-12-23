package com.twisted.minds.repolist.data.service

import com.twisted.minds.repolist.data.model.dto.PullItemDTO
import kotlinx.coroutines.flow.Flow

internal interface PullListService {

    fun getPull(
        author: String,
        repo: String,
    ): Flow<ArrayList<PullItemDTO>>
}
