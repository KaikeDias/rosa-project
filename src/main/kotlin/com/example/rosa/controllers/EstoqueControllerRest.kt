package com.example.rosa.controllers

import com.example.rosa.exceptions.ProdutoNaoEncontradoException
import com.example.rosa.models.Produto
import com.example.rosa.models.ProdutoDTO
import com.example.rosa.services.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/estoque/v1")
class EstoqueControllerRest(private val produtoService: ProdutoService) {
    @GetMapping
    fun listarProdutos(): ResponseEntity<List<Produto>> {
        val listaProdutos: List<Produto> = produtoService.listarProdutos()
        return ResponseEntity.ok(listaProdutos)
    }

    @PostMapping("/novo")
    fun criarProduto(@RequestBody novoProduto: ProdutoDTO): ResponseEntity<Produto> {
        val produtoCriado = produtoService.salvarProduto(novoProduto)

        return ResponseEntity(produtoCriado, HttpStatus.valueOf(201))
    }

    @DeleteMapping("/delete/{id}")
    fun deletarProduto(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            produtoService.deletarProduto(id)
            return ResponseEntity.noContent().build()
        } catch (e: ProdutoNaoEncontradoException) {
            return ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/estoque/alterar-status/{id}")
    fun alterarStatusProdut(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            produtoService.alterarStatusProduto(id)
            return ResponseEntity.ok(produtoService.buscarProdutoPorId(id))
        } catch (e: ProdutoNaoEncontradoException) {
            return ResponseEntity.notFound().build()
        }
    }
}