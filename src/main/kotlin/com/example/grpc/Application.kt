package com.example.grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class Application {
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}


@RestController
@RequestMapping("/test")
open class Controller(private val client: Client) {

    @GetMapping
    fun callClient() {
        client.sendMessage()
    }
}