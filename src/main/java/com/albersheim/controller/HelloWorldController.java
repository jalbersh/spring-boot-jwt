package com.albersheim.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}

    @RequestMapping({ "/booger" })
    public String booger() {
        return "Hello Booger";
    }
}
