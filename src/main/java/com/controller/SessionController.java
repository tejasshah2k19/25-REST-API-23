package com.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/")
public class SessionController {

	// public
	@PostMapping("/login")
	public ResponseEntity<?> login() {
		HashMap<String, Object> hm = new HashMap<>();

		hm.put("msg", "Login Success");
		hm.put("token", "111");

		return ResponseEntity.ok(hm);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup() {
		return ResponseEntity.ok("");
	}

	// forgetpassword

}
