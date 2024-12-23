package com.twisted.minds.repolist.data.usecase

import com.twisted.minds.repolist.data.model.vo.RepoVO
import kotlinx.coroutines.flow.Flow

internal interface GetRepoListUseCase {
    operator fun invoke(
        query: String,
        sort: String,
        page: Int
    ): Flow<List<RepoVO>>
}
