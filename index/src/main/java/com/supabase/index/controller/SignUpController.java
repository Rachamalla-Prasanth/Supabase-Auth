package com.supabase.index.controller;

import org.json.JSONObject;
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

import com.supabase.index.model.EmailSignUp;
import com.supabase.index.model.MagicLink;
import com.supabase.index.model.PhoneSignUp;

@RestController
public class SignUpController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${supabase.api.key}")
	private String apikey;

	@Value("${supabase.api.secret}")
	private String authorizationKey;
	
	@Value("${supabase.api.url}")
	private String supabaseUrl;
	
	@Value("${supabse.api.anon.key}")
	private String supabaseAnonKey;

	@PostMapping("/auth/v1/signup")
	public ResponseEntity<String> signup(@RequestBody EmailSignUp emailSignUp) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("apikey", apikey);

	    JSONObject requestBody = new JSONObject();
	    requestBody.put("email", emailSignUp.getEmail());
	    requestBody.put("password", emailSignUp.getPassword());

	    HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
	    ResponseEntity<String> response = restTemplate.exchange(
				supabaseUrl+"/auth/v1/signup", HttpMethod.POST, request,
				String.class);

		return response;
	}
	
	@PostMapping("/auth/v1/anon/signup")
	public ResponseEntity<String> signupUsingAnonKey(@RequestBody EmailSignUp emailSignUp) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("apikey", supabaseAnonKey);

	    JSONObject requestBody = new JSONObject();
	    requestBody.put("email", emailSignUp.getEmail());
	    requestBody.put("password", emailSignUp.getPassword());

	    HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
	    ResponseEntity<String> response = restTemplate.exchange(
				supabaseUrl+"/auth/v1/signup", HttpMethod.POST, request,
				String.class);

		return response;
	}
	
	@PostMapping("auth/v1/phoneSignup")
	public ResponseEntity<String> phoneSignup(@RequestBody PhoneSignUp phoneSignUp) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("apikey", apikey);

	    JSONObject requestBody = new JSONObject();
	    requestBody.put("phone", phoneSignUp.getPhone());
	    requestBody.put("password", phoneSignUp.getPassword());

	    HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
	    ResponseEntity<String> response = restTemplate.exchange(
				supabaseUrl+"/auth/v1/signup", HttpMethod.POST, request,
				String.class);
		return response;
	}
	
	@PostMapping("auth/v1/magicLink")
	public ResponseEntity<String> magicSignup(@RequestBody MagicLink magicLink) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("apikey", apikey);

	    JSONObject requestBody = new JSONObject();
	    requestBody.put("email", magicLink.getEmail());

	    HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
	    ResponseEntity<String> response = restTemplate.exchange(
				supabaseUrl+"/auth/v1/magiclink", HttpMethod.POST, request,
				String.class);
		return response;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
