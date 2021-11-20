package com.example.liberary.studentliberary.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>
{
   @Query("")
   User findByUsername(String s);

   //you have to write update password function

}
