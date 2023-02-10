package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepo;
import com.smart.entities.User;

public class UserDetailsServiceImple implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//fecting user from database-->>>

		User userByName = userRepo.getUserByUserName(username);
		if(userByName==null)
		{
			throw new UsernameNotFoundException("could not find user");
		}
		
		CustomUserDetails customUserDetails=new CustomUserDetails(userByName);
		return customUserDetails;
	}

}
