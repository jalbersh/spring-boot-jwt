package com.albersheim.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

	public JwtUserDetailsService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getHelloResource() {
		String result = restTemplate.getForObject("http://localhost:8080/hello", String.class);
		System.out.println("getHelloResource: " + result);

		return result;
	}

	public String getOtherResource() {
		String result = restTemplate.getForObject("http://localhost:8080/booger", String.class);
		System.out.println("getOtherResource: " + result);

		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("javainuse".equals(username)) {
			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}