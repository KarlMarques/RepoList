package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.repolist.data.model.dto.PullItemDTO

internal fun ArrayList<PullItemDTO>.toPullVOList() =
    this.map {
        it.toPullVO()
    }
