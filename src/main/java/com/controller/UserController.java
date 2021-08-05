package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "UserRestAPI")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// get user-list
	@ApiOperation(value = "Get All Users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> allUsers() {
		List<User> users = userService.allUsers();
		return ResponseEntity.ok(users);
	}
	
	// add user
	@ApiOperation(value = "Save New User")
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult result) {
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		userService.saveUser(user);
		return ResponseEntity.created(null).body(user);
	}
	
	// edit user
	@ApiOperation(value = "Update User")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result) {
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		try {
			userService.updateUser(user);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error!");
		}
		return ResponseEntity.ok(user);
	}
	
	// delete user
	@ApiOperation(value = "Delete User")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@ApiParam(value = "Article ID to delete", required = true, defaultValue = "0") @PathVariable("id") int id) {
		try {
			userService.deleteUser(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
		return ResponseEntity.ok(true);
	}
}
