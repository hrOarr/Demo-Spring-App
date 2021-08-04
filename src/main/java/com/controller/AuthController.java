package com.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> submitLogin(@Valid @RequestBody User user, BindingResult result) {
		
		if(result.hasFieldErrors("email")||result.hasFieldErrors("password")) {
			return ResponseEntity.badRequest().body(false);
		}
		// check user password
		User obj = userService.getUserByEmail(user.getEmail());
		System.out.println(obj);
		if(obj==null) {
			return ResponseEntity.badRequest().body(false);
		}
		else if(!obj.getPassword().equals(user.getPassword())) {
			return ResponseEntity.badRequest().body(false);
		}
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> submitRegister(@Valid @RequestBody User user, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(false);
		}
		// check if already exist or not
		User obj = userService.getUserByEmail(user.getEmail());
		if(obj!=null) {
			return ResponseEntity.badRequest().body(false);
		}
		userService.saveUser(user);
		return ResponseEntity.ok(userService.getUserByEmail(user.getEmail()));
	}
}
