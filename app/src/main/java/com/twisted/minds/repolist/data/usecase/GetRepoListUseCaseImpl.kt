package com.twisted.minds.repolist.data.usecase

import com.twisted.minds.repolist.data.mapper.toRepoVOList
import com.twisted.minds.repolist.data.repository.RepoListRepository
import kotlinx.coroutines.flow.map

internal class GetRepoListUseCaseImpl(
    private val repoListRepository: RepoListRepository
) : GetRepoListUseCase {

    override operator fun invoke(
        query: String,
        sort: String,
        page: Int
    ) =
        repoListRepository.getRepo(
            query,
            sort,
            page
        ).map {
            it.toRepoVOList()
        }
}
