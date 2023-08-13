package com.example.rosa.controllers
import com.example.rosa.models.Product1
import com.example.rosa.models.Produto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class EstoqueController {
    @GetMapping("/estoque")
    fun listarEstoque(model: Model): String {
        val product = Produto(
                "Nome do Produto",
                true,
                "Destinação do Produto",
                5,
                30,
                10
        )
        model.addAttribute("product", product)
        return "estoque"
    }
}