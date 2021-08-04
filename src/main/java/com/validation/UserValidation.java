package com.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

import com.model.User;

public class UserValidation {
	public boolean validate(User user, Model model) {
		String username = user.getName();
		String email = user.getEmail();
		//String country = user.getCountry();
		boolean error = false;
		
		// Name validation check
		String regex = "^(?=.{1,}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._\\s]*$+(?<![_.])$";
		if(username.length()<3) {
			error = true;
			if(username.matches(regex))model.addAttribute("username_error", "Name is too small");
			else model.addAttribute("username_error", "Invalid username");
		}
		else if(username.length()>15) {
			error = true;
			if(username.matches(regex))model.addAttribute("username_error", "Name is too long");
			else model.addAttribute("username_error", "Invalid username");
		}
		else if(!username.matches(regex)) {
			error = true;
			model.addAttribute("username_error", "Invalid username");
		}
		
		// Email validation check
		regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			error = true;
			model.addAttribute("email_error", "Invalid email");
		}
		
		return error;
	}
}
