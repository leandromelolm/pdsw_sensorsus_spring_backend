package br.com.sensorsus.sensorsus.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sensorsus.sensorsus.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}	
}
