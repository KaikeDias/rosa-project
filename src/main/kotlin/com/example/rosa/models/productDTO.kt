package com.example.rosa.models

data class ProdutoDTO(
        var nome: String? = null,
        var status: Boolean? = null,
        var destinacao: String? = null,
        var rentabilidade: Int? = null,
        var prazo: Int? = null,
        var taxAdmin: Int? = null
)