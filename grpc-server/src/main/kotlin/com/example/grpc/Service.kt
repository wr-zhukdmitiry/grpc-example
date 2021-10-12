package com.example.grpc

import com.poc.grpc.GreetingRequest
import com.poc.grpc.GreetingResponse
import com.poc.grpc.GreetingServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class Service : GreetingServiceGrpc.GreetingServiceImplBase() {

    override fun greet(request: GreetingRequest, responseObserver: StreamObserver<GreetingResponse>) {
        val response = GreetingResponse.newBuilder()
            .setResponse("Hi ${request.greeting.firstNumber} ${request.greeting.secondNumber}")
            .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}