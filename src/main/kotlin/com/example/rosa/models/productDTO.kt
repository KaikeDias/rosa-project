package com.example.rosa.models

import java.util.*

data class ProdutoDTO(
        var nome: String? = null,
        var status: Boolean? = null,
        var destinacao: String? = null,
        var rentabilidade: Int? = null,
        var prazo: Int? = null,
        var taxAdmin: Int? = null,
        var vencimento: Int? = null
)