package com.vladislaviliev.airservers.resource

import com.vladislaviliev.airservers.resource.reading.dao.Dao
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

    @Test
    fun exposesSquare() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/square?lat=0.0&lng=0.0&side=10.0"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    fun square_fails_without_lat() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/square?lng=0.0&side=10.0"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun square_fails_without_lng() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/square?lat=0.0&side=10.0"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun square_fails_without_side() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/square?lat=0.0&lng=0.0"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun exposesPost() {
        mockMvc
            .perform(MockMvcRequestBuilders.post("").contentType(MediaType.APPLICATION_JSON).content("[]"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}