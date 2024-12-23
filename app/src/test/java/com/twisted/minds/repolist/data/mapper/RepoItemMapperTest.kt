package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.testcommons.mock.mockRepoItemDTO
import com.twisted.minds.testcommons.mock.mockRepoVO
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RepoItemMapperTest {

    @Test
    fun givenMapRepoItemDTOToRepoVO_thenReturnRepoVO() {

        val repoItemDTO = mockRepoItemDTO(1)
        val repoVO = mockRepoVO(1)
        val result = repoItemDTO.toRepoVO()

        assertEquals(result, repoVO)
    }
}
