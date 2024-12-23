package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.testcommons.mock.mockPullItemDTO
import com.twisted.minds.testcommons.mock.mockPullVO
import org.junit.Assert.assertEquals
import org.junit.Test

internal class PullItemMapperTest {

    @Test
    fun givenMapPullItemDTOToPullVO_thenReturnPullVO() {

        val pullItemDTO = mockPullItemDTO(1)
        val repoVO = mockPullVO(1)
        val result = pullItemDTO.toPullVO()

        assertEquals(result, repoVO)
    }
}
