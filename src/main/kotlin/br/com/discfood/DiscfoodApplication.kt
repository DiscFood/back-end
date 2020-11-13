package br.com.discfood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiscfoodApplication

fun main(args: Array<String>) {
    runApplication<DiscfoodApplication>(*args)
}
