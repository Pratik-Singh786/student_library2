package com.example.liberary.studentliberary.Controller;

import com.example.liberary.studentliberary.Model.Author;
import com.example.liberary.studentliberary.Model.Book;
import com.example.liberary.studentliberary.Model.Genre;
import com.example.liberary.studentliberary.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")  //to refactor more number of apis;
public class BookController
{
    @Autowired
    BookService bookService;

    @PostMapping("/") //
    public ResponseEntity createBook(@RequestBody Book book)
    {
        bookService.createBook(book);//its  clear how to post request using constructor parameters
        return  new ResponseEntity<>("the book is added sucessfully", HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity getBooks(@RequestParam(value = "genre", required = false)String genre
                                  ,@RequestParam(value="available", required=false,defaultValue="false") boolean available
                                   ,@RequestParam(value="author",required=false)String author)

    {
        //genre+author
        //genre
        //author

        List<Book> bookList= bookService.getBooks(genre,available,author);

    return  new ResponseEntity<>(bookList,HttpStatus.OK);
    }





}
