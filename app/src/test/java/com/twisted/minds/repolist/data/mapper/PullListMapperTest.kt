package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.testcommons.mock.mockPullListDTO
import com.twisted.minds.testcommons.mock.mockPullVOList
import org.junit.Assert.assertEquals
import org.junit.Test

internal class PullListMapperTest {

    @Test
    fun givenMapPullListDTOToListPullVO_thenReturnListPullVO() {

        val pullListDTO = mockPullListDTO(30)
        val listPullVO = mockPullVOList(30)
        val result = pullListDTO.toPullVOList()

        assertEquals(result, listPullVO)
    }
}
