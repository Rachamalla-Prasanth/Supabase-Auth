package com.supabase.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${supabase.api.key}")
	private String apikey;

	@Value("${supabase.api.secret}")
	private String authorizationKey;

	@Value("${supabse.api.anon.key}")
	private String supabaseAnonKey;

	@SuppressWarnings("deprecation")
	@GetMapping("/v1/getAllEmployees")
	public ResponseEntity<Object> getEmployeeById(HttpServletRequest httpServletRequest) {		
		String token = httpServletRequest.getHeader("Authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", apikey);
		headers.set("Authorization",token);

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString("https://svdttiuunlxjxmmqcqtn.supabase.co/rest/v1/Employee").queryParam("select", "*");
		String uriString = builder.toUriString();
		ResponseEntity<Object> response = restTemplate.exchange(uriString, HttpMethod.GET, requestEntity, Object.class);
		return response;
	}
}
