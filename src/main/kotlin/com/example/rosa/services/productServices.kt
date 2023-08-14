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

//    fun buscarProdutoPorId(id: Long): Produto? {
//        val produto: Produto? = produtoRepository.findByIdOrNull(id)
//        return produto
//    }

    fun listarProdutos(): List<Produto> {
        return produtoRepository.findAll()
    }

//    fun alterarStatus(id: Long) {
//        val produtoAntigo = buscarProdutoPorId(id)
//
//        if(produtoAntigo != null) {
//            val novoStatus = !produtoAntigo.status
//            val produtoAlterado = produtoAntigo.copy(status = novoStatus)
//            produtoRepository.save(produtoAlterado)
//        }
//
//    }

//    fun buscarPorNome(nome: String): Produto? {
//        val listaProdutos = listarProdutos()
//    }

    fun deletarProduto(id: Long) {
        produtoRepository.deleteById(id)
    }
}