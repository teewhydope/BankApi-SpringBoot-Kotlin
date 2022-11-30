package com.teewhy.bank.springboot.bankapi.controller.v1.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.teewhy.bank.springboot.bankapi.entity.BankEntity
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    private val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status {
                        isOk()
                        content { contentType(MediaType.APPLICATION_JSON) }
                    }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/bank/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = "12345"

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }

        /*    @Test
            fun `should return null if account number does not exist`() {
                // given
                val accountNumber = "does_not_exist"

                // when

                mockMvc.get("$baseUrl/$accountNumber")
                    .andDo { print() }
                    .andExpect {
                        jsonPath("$") { value("") }
                    }

                // then
            }*/
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddBank {

        @Test
        fun `should add the new bank`() {
            // given
            val newBank = BankEntity("11111", 0.0, 0)

            // when
            val response = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            response.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("11111") }
                }
        }

        /* @Test
         fun `should return BAD REQUEST if bank with given account number already exist`() {
             // given
             val invalidBank = Bank("12345", 0.0, 0)

             // when
             val response = mockMvc.post(baseUrl) {
                 contentType = MediaType.APPLICATION_JSON
                 content = objectMapper.writeValueAsString(invalidBank)
             }

             // then
             response.andDo { print() }
                 .andExpect {
                     status { isBadRequest() }
                     content { contentType(MediaType.APPLICATION_JSON) }
                 }
         }*/
    }
}
