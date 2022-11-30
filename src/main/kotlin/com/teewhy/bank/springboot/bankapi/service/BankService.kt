package com.teewhy.bank.springboot.bankapi.service

import com.teewhy.bank.springboot.bankapi.entity.BankEntity

interface BankService {
    fun getBanks(): Collection<BankEntity>
    fun getBank(accountNumber: String): BankEntity?
    fun addBank(bank: BankEntity): BankEntity
    fun deleteBank(accountNumber: String)
    fun editBank(bank: BankEntity): BankEntity
}
