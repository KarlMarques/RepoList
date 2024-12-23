package com.twisted.minds.testcommons.mock

import com.twisted.minds.repolist.data.model.dto.RepoItemDTO
import com.twisted.minds.repolist.data.model.dto.RepoListDTO
import com.twisted.minds.repolist.data.model.dto.UserDTO

internal fun mockRepoListDTO(size: Int): RepoListDTO {
    val items = arrayListOf<RepoItemDTO>().also {
        for (i in 1..size) {
            it.add(mockRepoItemDTO(i))
        }
    }

    return RepoListDTO(
        items = items
    )
}

internal fun mockRepoItemDTO(index: Int) =
    RepoItemDTO(
        name = "name ${index}",
        description = "description ${index}",
        stars = index.toDouble(),
        forks = index.toDouble(),
        owner = mockUserDTO(index),
    )

internal fun mockUserDTO(index: Int) =
    UserDTO(
        userPicture = "userPicture ${index}",
        userName = "userName ${index}",
    )
