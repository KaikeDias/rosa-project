package com.example.rosa.controllers
import com.example.rosa.models.Produto
import com.example.rosa.models.ProdutoDTO
import com.example.rosa.services.produtoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class EstoqueController(private val produtoService: produtoService) {
    @GetMapping("/estoque")
    fun listarEstoque(model: Model): String {
        val listaProdutos: List<Produto> = produtoService.listarProdutos()
        model.addAttribute("produtos", listaProdutos)
        return "listarProdutos"
    }

    @GetMapping("/estoque/novo")
    fun exibirFormularioDeCriacao(model: Model): String {
        model.addAttribute("produtoDTO", ProdutoDTO())
        return "novoProduto"
    }

   @PostMapping("/estoque/novo")
   fun criarProduto(@ModelAttribute produtoDTO: ProdutoDTO): String {
       produtoService.salvarProduto(produtoDTO)

       return "redirect:/estoque"
   }

   @PostMapping("/estoque/delete/{id}")
   fun deletarProduto(@PathVariable id: Long): String {
        produtoService.deletarProduto(id)

       return "redirect:/estoque"
   }

   @PostMapping("estoque/alterar-status/{id}")
   fun alterarStatus(@PathVariable id: Long): String {
       produtoService.alterarStatusProduto(id)

       return "redirect:/estoque"
   }
}