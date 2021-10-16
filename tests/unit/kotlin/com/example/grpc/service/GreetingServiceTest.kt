package com.example.grpc.service

import com.example.grpc.Greeting
import com.example.grpc.GreetingRequest
import com.example.grpc.GreetingResponse
import com.example.grpc.GreetingService
import io.grpc.internal.testing.StreamRecorder
import io.mockk.junit5.MockKExtension
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GreetingServiceTest {

    private val greetingService: GreetingService = GreetingService()

    @Test
    fun `should return response`() {
        val greeting = Greeting.newBuilder().setFirstName("First").setLastName("Last").build()
        val greetingRequest = GreetingRequest.newBuilder().setGreeting(greeting).build()
        val recorder = StreamRecorder.create<GreetingResponse>()
        greetingService.greet(greetingRequest, recorder)

        if (!recorder.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(recorder.error)
        val values = recorder.values
        assertEquals(1, values.size)
        val greetingResponse = values[0]
        assertEquals(
            GreetingResponse.newBuilder().setResponse("Hi First Last").build(), greetingResponse
        )
    }
}