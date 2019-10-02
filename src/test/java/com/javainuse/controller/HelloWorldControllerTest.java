package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import org.springframework.test.web.servlet.MockMvc;
import import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    MockRestServiceServer server;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void hello() {
        // given
        server.expect(requestTo(
                String.format("http://localhost:8080/findSummer")))
                .andRespond(withSuccess("{\"total\":10,\"min\":1,\"max\":5}", MediaType.APPLICATION_JSON))
        ;

        // when
        JsonObject jsonObject = new JsonObject();
        JsonObject response = this.put("http://localhost:8080/findSummer", jsonObject).getAsJsonObject();

        // then
        JsonObject result = response.getAsJsonObject("solutions");

        assertThat(result, CoreMatchers.equalTo(expectedCombos));

        server.verify();
    }

    @org.junit.jupiter.api.Test
    void booger() {
    }
}