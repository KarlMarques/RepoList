package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.repolist.data.model.dto.RepoItemDTO
import com.twisted.minds.repolist.data.model.vo.RepoVO

internal fun RepoItemDTO.toRepoVO() =
    RepoVO(
        name = name,
        description = description,
        authorName = owner.userName,
        authorPicture = owner.userPicture,
        stars = stars.toInt().toString(),
        forks = forks.toInt().toString(),
    )
