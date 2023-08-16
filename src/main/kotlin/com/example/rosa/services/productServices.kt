package com.example.rosa.services

import com.example.rosa.models.Produto
import com.example.rosa.models.ProdutoDTO
import com.example.rosa.models.ProdutoStatus
import com.example.rosa.repository.ProdutoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class produtoService(private val produtoRepository: ProdutoRepository) {
    fun salvarProduto(produtoDTO: ProdutoDTO): Produto {
        val produto = Produto(
                nome = produtoDTO.nome!!,
                status = ProdutoStatus.available,
                destinacao = produtoDTO.destinacao!!,
                rentabilidade = produtoDTO.rentabilidade!!,
                prazo = produtoDTO.prazo!!,
                taxAdmin = produtoDTO.taxAdmin!!
        )

        return produtoRepository.save(produto)
    }

    fun buscarProdutoPorId(id: Long): Produto? {
        val produto = produtoRepository.findById(id)
                .orElseThrow { NoSuchElementException("Produto não encontrado com ID: $id") }
        return produto
    }

    fun listarProdutos(): List<Produto> {
        return produtoRepository.findAll()
    }

    fun alterarStatusProduto(id: Long): Produto? {
        val produto = buscarProdutoPorId(id)

        // Alterna o status
        produto?.status = if (produto?.status == ProdutoStatus.available) {
            ProdutoStatus.unavailable
        } else {
            ProdutoStatus.available
        }

        if(produto != null) {
            return produtoRepository.save(produto)
        }
        return null
    }

    fun deletarProduto(id: Long) {
        produtoRepository.deleteById(id)
    }
}