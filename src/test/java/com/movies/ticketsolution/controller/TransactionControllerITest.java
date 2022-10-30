package com.movies.ticketsolution.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.ticketsolution.TicketSolutionApplication;
import com.movies.ticketsolution.dto.TransactionRequest;
import com.movies.ticketsolution.dto.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TicketSolutionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TransactionControllerITest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testBasicTestCases() {
        executeTestCase("test_case_1");
        executeTestCase("test_case_2");
        executeTestCase("test_case_3");
    }

    private void executeTestCase(String testCaseIdentifier) {
        try {
            String requestFilePath = String.format("src/test/resources/data/%s_request.json", testCaseIdentifier);
            String responseFilePath = String.format("src/test/resources/data/%s_response.json", testCaseIdentifier);
            TransactionRequest request = readRequest(requestFilePath);
            TransactionResponse expectedResponse = readResponse(responseFilePath);
            TransactionResponse actualResponse = restTemplate.exchange("http://localhost:8080/process",
                    HttpMethod.POST,
                    new HttpEntity<TransactionRequest>(request),
                    TransactionResponse.class).getBody();
            assertEquals(expectedResponse, actualResponse);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    private TransactionRequest readRequest(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get(path).toFile(), TransactionRequest.class);
    }

    private TransactionResponse readResponse(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get(path).toFile(), TransactionResponse.class);
    }
}