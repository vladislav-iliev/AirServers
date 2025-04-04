package com.vladislaviliev.airserver

import com.vladislaviliev.airserver.reading.dao.Dao
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@MockitoBean(types = [Dao::class])
class WebTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun exposesRoot() {
        mockMvc
            .perform(MockMvcRequestBuilders.get(""))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }
}