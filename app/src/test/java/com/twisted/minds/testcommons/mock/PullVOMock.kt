package com.twisted.minds.testcommons.mock

import com.twisted.minds.repolist.data.model.vo.PullVO

internal fun mockPullVOList(size: Int) =
    mutableListOf<PullVO>().also { buildList ->
        for (i in 1..size) {
            buildList.add(mockPullVO(i))
        }
    }

internal fun mockPullVO(index: Int) =
    PullVO(
        userName = "userName ${index}",
        userPicture = "userPicture ${index}",
        title = "title ${index}",
        date = "16 Dec 2024, at 08:09:21",
        body = "body ${index}",
    )
