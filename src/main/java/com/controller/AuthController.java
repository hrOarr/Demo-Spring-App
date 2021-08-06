package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.LoginDTO;
import com.model.RegisterDTO;
import com.model.User;
import com.service.UserService;
import com.utility.Convertor;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private UserService userService;
	private static Convertor convertor = new Convertor();
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Login API")
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		// check user password
		User obj = userService.getUserByEmail(loginDTO.getEmail());
		if(obj==null) {
			List<String> errors = new ArrayList<String>();
			errors.add("Email is incorrect!");
			return ResponseEntity.badRequest().body(errors);
		}
		else if(!obj.getPassword().equals(loginDTO.getPassword())) {
			List<String> errors = new ArrayList<String>();
			errors.add("Password is incorrect!");
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(obj);
	}
	
	@ApiOperation(value = "Register API")
	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@Valid @RequestBody RegisterDTO registerDTO, BindingResult result) {
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		// check if already exist or not
		User obj = userService.getUserByEmail(registerDTO.getEmail());
		if(obj!=null) {
			List<String> errors = new ArrayList<String>();
			errors.add("Email is already in use!");
			return ResponseEntity.badRequest().body(errors);
		}
		userService.saveUser(convertor.registerDTOtoUser(registerDTO));
		return ResponseEntity.ok(userService.getUserByEmail(registerDTO.getEmail()));
	}
}
