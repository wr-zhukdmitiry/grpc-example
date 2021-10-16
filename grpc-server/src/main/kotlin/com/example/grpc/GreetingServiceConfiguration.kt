package com.example.grpc

import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GreetingServiceConfiguration {

    @Bean
    @GrpcService
    open fun greetingService(): GreetingService {
        return GreetingService()
    }
}