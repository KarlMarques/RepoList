package com.twisted.minds.repolist.data.usecase

import com.twisted.minds.repolist.data.model.vo.PullVO
import kotlinx.coroutines.flow.Flow

internal interface GetPullListUseCase {
    operator fun invoke(
        author: String,
        repo: String
    ): Flow<List<PullVO>>
}
