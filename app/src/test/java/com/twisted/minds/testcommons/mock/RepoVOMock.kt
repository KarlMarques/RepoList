package com.twisted.minds.testcommons.mock

import com.twisted.minds.repolist.data.model.vo.RepoVO

internal fun mockRepoVOList(size: Int) =
    mutableListOf<RepoVO>().also { buildList ->
        for (i in 1..size) {
            buildList.add(mockRepoVO(i))
        }
    }

internal fun mockRepoVO(index: Int) =
    RepoVO(
        name = "name ${index}",
        description = "description ${index}",
        authorName = "userName ${index}",
        authorPicture = "userPicture ${index}",
        stars = index.toString(),
        forks = index.toString(),
    )
