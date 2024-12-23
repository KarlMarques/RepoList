package com.twisted.minds.testcommons.mock

import com.twisted.minds.repolist.data.model.dto.PullItemDTO

internal fun mockPullListDTO(size: Int) =
    arrayListOf<PullItemDTO>().also {
        for (i in 1..size) {
            it.add(mockPullItemDTO(i))
        }
    }

internal fun mockPullItemDTO(index: Int) =
    PullItemDTO(
        title = "title ${index}",
        date = "2024-12-16T08:09:21Z",
        body = "body ${index}",
        user = mockUserDTO(index),
    )
