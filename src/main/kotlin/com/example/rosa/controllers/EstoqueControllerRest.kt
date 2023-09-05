package com.example.rosa.controllers

import com.example.rosa.exceptions.ProdutoNaoEncontradoException
import com.example.rosa.models.Produto
import com.example.rosa.models.ProdutoDTO
import com.example.rosa.services.ProdutoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    @Operation(summary = "Lista todos os produtos")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Produtos encontrados", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Produto::class)))))])]
    )
    @GetMapping
    fun listarProdutos(): ResponseEntity<List<Produto>> {
        val listaProdutos: List<Produto> = produtoService.listarProdutos()

        return ResponseEntity.ok(listaProdutos)
    }

    @Operation(summary = "Cria um novo produto")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Produto criado", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Produto::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])]
    )
    @PostMapping("/novo")
    fun criarProduto(@RequestBody novoProduto: ProdutoDTO): ResponseEntity<Produto> {
        val produtoCriado = produtoService.salvarProduto(novoProduto)

        return ResponseEntity(produtoCriado, HttpStatus.valueOf(201))
    }

    @Operation(summary = "Deleta um produto")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Produto deletado", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Produto::class)))))]),
        ApiResponse(responseCode = "404", description = "Produto nao encontrado", content = [Content()])]
    )
    @DeleteMapping("/delete/{id}")
    fun deletarProduto(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            produtoService.deletarProduto(id)
            return ResponseEntity.noContent().build()
        } catch (e: ProdutoNaoEncontradoException) {
            return ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Altera o status do produto")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Status alterado", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Produto::class)))))]),
        ApiResponse(responseCode = "404", description = "Produto nao encontrado", content = [Content()])]
    )
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