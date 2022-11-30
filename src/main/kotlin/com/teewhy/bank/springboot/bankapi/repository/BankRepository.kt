package com.teewhy.bank.springboot.bankapi.repository

import com.teewhy.bank.springboot.bankapi.entity.BankEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository : JpaRepository<BankEntity, String>
