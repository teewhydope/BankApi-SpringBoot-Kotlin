package com.teewhy.bank.springboot.bankapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "bank")
data class BankEntity(
    @Id
    @Column(name = "account_number")
    val accountNumber: String,

    @Column(name = "trust")
    val trust: Double,

    @Column(name = "transaction_fee")
    val transactionFee: Int
)
