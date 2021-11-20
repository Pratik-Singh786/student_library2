package com.example.liberary.studentliberary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;
    //if you want this method loadUserByUsername this method can load user from db,cache or any third party service


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        return userRepository.findByUsername( s);
    }


}
