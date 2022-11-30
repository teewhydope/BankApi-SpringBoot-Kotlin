package com.teewhy.bank.springboot.bankapi.service

import com.teewhy.bank.springboot.bankapi.repository.BankRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BankServiceTest {

    @Autowired
    private lateinit var repository: BankRepository

    private lateinit var bankService: BankService

    @BeforeEach
    fun setUp() {
        bankService = BankServiceImpl(repository)
    }

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    @Test
    fun `should call its repository to retrieve banks`() {
        // given

        // when
        val banks = bankService.getBanks()

        // then
        assertThat(banks).isNotNull
    }
}
