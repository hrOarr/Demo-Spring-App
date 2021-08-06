package com.utility;

import com.model.RegisterDTO;
import com.model.User;

public class Convertor {
	
	public User registerDTOtoUser(RegisterDTO registerDTO) {
		User user = new User();
		user.setName(registerDTO.getName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(registerDTO.getPassword());
		
		return user;
	}
}
