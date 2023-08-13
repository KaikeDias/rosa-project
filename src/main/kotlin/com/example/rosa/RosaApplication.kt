package com.example.rosa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class RosaApplication

@Controller
class foo {
    @GetMapping("/")
    fun redirect(): String {
        return "redirect:/home"
    }
}

fun main(args: Array<String>) {
    runApplication<RosaApplication>(*args)
}
