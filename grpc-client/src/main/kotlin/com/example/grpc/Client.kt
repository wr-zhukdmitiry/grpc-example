package com.example.grpc

import com.poc.grpc.Greeting
import com.poc.grpc.GreetingRequest
import com.poc.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub

class Client(private val greetingStub: GreetingServiceBlockingStub) {

    fun sendMessage() {
        val greeting = Greeting.newBuilder().setFirstNumber("1").setSecondNumber("2").build()
        val request = GreetingRequest.newBuilder().setGreeting(greeting).build()
        println(greetingStub.greet(request).response)
    }
}