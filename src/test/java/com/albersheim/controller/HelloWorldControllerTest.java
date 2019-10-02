package com.albersheim.controller;

import com.albersheim.service.JwtUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class) //SpringRunner is an alias for the SpringJUnit4ClassRunner
@SpringBootTest
public class HelloWorldControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    JwtUserDetailsService service;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void hello() {

        mockServer.expect(requestTo("http://localhost:8080/hello")).andRespond(withSuccess("Hello World", MediaType.TEXT_PLAIN));

        String result = service.getHelloResource();
        System.out.println("testGetRootResource: " + result);

        mockServer.verify();
        assertEquals("Hello World", result);
    }

    @Test
    public void booger() {
        mockServer.expect(requestTo("http://localhost:8080/booger")).andRespond(withSuccess("Hello Booger", MediaType.TEXT_PLAIN));

        String result = service.getOtherResource();
        System.out.println("testGetRootResource: " + result);

        mockServer.verify();
        assertEquals("Hello Booger", result);
    }
}