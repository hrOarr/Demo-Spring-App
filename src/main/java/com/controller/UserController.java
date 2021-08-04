package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// get user-list
	@GetMapping()
	public ResponseEntity<List<User>> allUsers() {
		List<User> users = userService.allUsers();
		return ResponseEntity.ok(users);
	}
	
	// add user
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(false);
		}
		System.out.println(user);
		userService.saveUser(user);
		return ResponseEntity.created(null).body(user);
	}
	
	// edit user
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(false);
		}
		userService.updateUser(user);
		return ResponseEntity.ok(user);
	}
	
	// delete user
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(true);
	}
}
