package com.example.grpc.service

import com.example.grpc.Client
import com.example.grpc.GreetingServiceConfiguration
import com.example.grpc.GreetingServiceGrpc
import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration
import net.devh.boot.grpc.client.inject.GrpcClient
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration
import net.devh.boot.grpc.server.autoconfigure.GrpcServerFactoryAutoConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
    properties = [
        "grpc.server.inProcessName=test",
        "grpc.server.port=-1",
        "grpc.client.inProcess.address=in-process:test"
    ]
)
@ContextConfiguration(
    classes = [GreetingServiceConfiguration::class,
        GrpcServerAutoConfiguration::class,
        GrpcServerFactoryAutoConfiguration::class,
        GrpcClientAutoConfiguration::class
    ]
)
@DirtiesContext
class GreetingServiceIntegrationTest {

    @GrpcClient("inProcess")
    private lateinit var greetingStub: GreetingServiceGrpc.GreetingServiceBlockingStub

    @Test
    @DirtiesContext
    fun `should return response`() {
        //given
        val client = Client(greetingStub)
        val firstName = "First"
        val lastName = "Last"
        //when
        val response = client.sendMessage(firstName, lastName)
        //then
        assertNotNull(response)
        assertEquals("Hi $firstName $lastName", response.response)
    }

}