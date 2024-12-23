package com.twisted.minds.repolist.data.usecase

import com.twisted.minds.repolist.data.mapper.toPullVOList
import com.twisted.minds.repolist.data.repository.PullListRepository
import kotlinx.coroutines.flow.map

internal class GetPullListUseCaseImpl(
    private val pullListRepository: PullListRepository
) : GetPullListUseCase {

    override operator fun invoke(
        author: String,
        repo: String
    ) =
        pullListRepository.getPull(
            author,
            repo
        ).map {
            it.toPullVOList()
        }
}
