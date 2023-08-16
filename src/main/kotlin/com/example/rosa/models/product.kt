package com.example.rosa.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
data class Produto(
        @Column(nullable = false)
        val nome: String,
        @Column(nullable = false)
              var status: ProdutoStatus,
        @Column(nullable = false)
              val destinacao: String,
        @Column(nullable = false)
              val rentabilidade: Int,
        @Column(nullable = false)
              val prazo: Int,
        @Column(nullable = false)
              val taxAdmin: Int) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

enum class ProdutoStatus {
    available,
    unavailable
}