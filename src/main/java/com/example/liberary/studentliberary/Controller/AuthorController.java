package com.example.liberary.studentliberary.Controller;

import com.example.liberary.studentliberary.Model.Author;
import com.example.liberary.studentliberary.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    AuthorService authorService;

  @PostMapping("/")
    public ResponseEntity getAuthor(@RequestBody Author author)
  {
     authorService.create(author);
     return new ResponseEntity<>("the author is sucessfully added to the system", HttpStatus.CREATED);

  }

   /* @PostMapping("/updateAuthor")
    public ResponseEntity updateAuthor(@RequestBody Author author)
    {
        authorService.updateAuthor(author);
        return new ResponseEntity<>("the author is updated sucessfully ", HttpStatus.ACCEPTED);

    }*/




}
