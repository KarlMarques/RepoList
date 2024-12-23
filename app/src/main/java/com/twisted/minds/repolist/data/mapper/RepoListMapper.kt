package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.repolist.data.model.dto.RepoListDTO

internal fun RepoListDTO.toRepoVOList() =
    items.map {
        it.toRepoVO()
    }
