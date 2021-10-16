package com.example.grpc

import io.grpc.stub.StreamObserver

class GreetingService : GreetingServiceGrpc.GreetingServiceImplBase() {

    override fun greet(request: GreetingRequest, responseObserver: StreamObserver<GreetingResponse>) {
        val response = GreetingResponse.newBuilder()
            .setResponse("Hi ${request.greeting.firstName} ${request.greeting.lastName}")
            .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}