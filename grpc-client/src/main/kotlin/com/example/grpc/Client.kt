package com.example.grpc


class Client(private val greetingStub: GreetingServiceGrpc.GreetingServiceBlockingStub) {

    fun sendMessage(firstName: String, lastName: String): GreetingResponse {
        val greeting = Greeting.newBuilder().setFirstName(firstName).setLastName(lastName).build()
        val request = GreetingRequest.newBuilder().setGreeting(greeting).build()
        return greetingStub.greet(request)
    }
}