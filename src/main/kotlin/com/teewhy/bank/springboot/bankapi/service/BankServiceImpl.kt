package com.teewhy.bank.springboot.bankapi.service

import com.teewhy.bank.springboot.bankapi.entity.BankEntity
import com.teewhy.bank.springboot.bankapi.repository.BankRepository
import org.springframework.stereotype.Service

@Service
class BankServiceImpl(private val repository: BankRepository) : BankService {

    override fun getBanks(): Collection<BankEntity> = repository.findAll()

    override fun getBank(accountNumber: String): BankEntity = repository.findById(accountNumber).orElse(null)

    override fun addBank(bank: BankEntity) = repository.save(bank)

    override fun deleteBank(accountNumber: String) = repository.deleteById(accountNumber)

    override fun editBank(bank: BankEntity): BankEntity {
        repository.findById(bank.accountNumber).orElseThrow()
        repository.save(bank)
        return bank
    }
}
