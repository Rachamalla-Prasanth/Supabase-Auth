package com.supabase.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supabase.index.model.EmailSignUp;
import com.supabase.index.model.PhoneLink;
import com.supabase.index.model.VerifyPhone;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${supabase.api.key}")
	private String apikey;

	@Value("${supabase.api.secret}")
	private String authorizationKey;
	
	@Value("${supabse.api.anon.key}")
	private String supabaseAnonKey;
	
	@PostMapping("auth/v1/login")
	public String login(@RequestBody EmailSignUp emailSignUp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", apikey);

		ObjectMapper obj = new ObjectMapper();
		String writeValueAsString = null;
		try {
			writeValueAsString = obj.writeValueAsString(emailSignUp);
		HttpEntity<String> requestEntity = new HttpEntity<>(writeValueAsString, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://svdttiuunlxjxmmqcqtn.supabase.co/auth/v1/token?grant_type=password", HttpMethod.POST, requestEntity,
				String.class);

		return response.getBody();
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@PostMapping("auth/v1/anon/login")
	public String loginUsingAnonKey(@RequestBody EmailSignUp emailSignUp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", apikey);

		ObjectMapper obj = new ObjectMapper();
		String writeValueAsString = null;
		try {
			writeValueAsString = obj.writeValueAsString(emailSignUp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		HttpEntity<String> requestEntity = new HttpEntity<>(writeValueAsString, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://svdttiuunlxjxmmqcqtn.supabase.co/auth/v1/token?grant_type=password", HttpMethod.POST, requestEntity,
				String.class);

		return response.getBody();
	}
	
	@PostMapping("auth/v1/phone/login")
	public String otpPhone(@RequestBody PhoneLink phoneLink) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", apikey);

		ObjectMapper obj = new ObjectMapper();
		String writeValueAsString = null;
		try {
			writeValueAsString = obj.writeValueAsString(phoneLink);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		HttpEntity<String> requestEntity = new HttpEntity<>(writeValueAsString, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://svdttiuunlxjxmmqcqtn.supabase.co/auth/v1/otp", HttpMethod.POST, requestEntity,
				String.class);

		return response.getBody();
	}
	
	@PostMapping("auth/v1/phone/verify")
	public String verifyPhone(@RequestBody VerifyPhone verifyPhone) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", apikey);

		ObjectMapper obj = new ObjectMapper();
		String writeValueAsString = null;
		try {
			writeValueAsString = obj.writeValueAsString(verifyPhone);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		HttpEntity<String> requestEntity = new HttpEntity<>(writeValueAsString, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://svdttiuunlxjxmmqcqtn.supabase.co/auth/v1/verify", HttpMethod.POST, requestEntity,
				String.class);

		return response.getBody();
	}
	
	@GetMapping("auth/v1/user")
	public String getUser(HttpServletRequest httpServletRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", apikey);
		headers.set("Authorization", httpServletRequest.getHeader("Authorization"));

		HttpEntity<String> requestEntity=new  HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://svdttiuunlxjxmmqcqtn.supabase.co/auth/v1/user", HttpMethod.GET, requestEntity,
				String.class);

		return response.getBody();
	}
}
