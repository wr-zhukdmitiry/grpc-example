package com.example.grpc

import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class ExceptionAdviser {

    @GrpcExceptionHandler
    fun handleError(exception: Exception): Status {
        return Status.UNKNOWN.withDescription("Your description").withCause(exception)
    }
}