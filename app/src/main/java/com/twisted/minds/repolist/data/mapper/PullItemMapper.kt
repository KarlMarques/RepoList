package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.repolist.data.model.dto.PullItemDTO
import com.twisted.minds.repolist.data.model.vo.PullVO
import com.twisted.minds.repolist.extension.dateFormatter
import com.twisted.minds.repolist.extension.orValue

internal fun PullItemDTO.toPullVO() =
    PullVO(
        userName = user.userName,
        userPicture = user.userPicture,
        title = title,
        date = dateFormatter(date),
        body = body.orValue("Description not provided"),
    )
