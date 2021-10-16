package com.example.grpc

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ClientConfiguration {

    @Bean
    @GrpcClient("greeting")
    open fun client(greetingStub: GreetingServiceGrpc.GreetingServiceBlockingStub): Client {
        return Client(greetingStub)
    }
}