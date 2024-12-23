package com.twisted.minds.repolist.data.mapper

import com.twisted.minds.testcommons.mock.mockRepoListDTO
import com.twisted.minds.testcommons.mock.mockRepoVOList
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RepoListMapperTest {

    @Test
    fun givenMapRepoListDTOToListRepoVO_thenReturnListRepoVO() {

        val repoListDTO = mockRepoListDTO(30)
        val listRepoVO = mockRepoVOList(30)
        val result = repoListDTO.toRepoVOList()

        assertEquals(result, listRepoVO)
    }
}
