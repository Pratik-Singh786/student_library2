package com.example.liberary.studentliberary.Repository;

import com.example.liberary.studentliberary.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<Author,Integer>
{
}

