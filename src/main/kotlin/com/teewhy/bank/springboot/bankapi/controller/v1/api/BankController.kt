package com.teewhy.bank.springboot.bankapi.controller.v1.api

import com.teewhy.bank.springboot.bankapi.entity.BankEntity
import com.teewhy.bank.springboot.bankapi.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/banks")
class BankController {

    @Autowired
    lateinit var service: BankService

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getBanks(): Collection<BankEntity> = service.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): BankEntity? = service.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: BankEntity): BankEntity = service.addBank(bank)

    @DeleteMapping("/{accountNumber}")
    fun deleteBank(@PathVariable accountNumber: String) = service.deleteBank(accountNumber)

    @PutMapping
    fun editBank(@RequestBody bank: BankEntity): BankEntity = service.editBank(bank)
}
