package com.example.liberary.studentliberary.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(unique = true)
    private String email;

    private int age;
    private String country;

    @OneToMany(mappedBy = "author",cascade=CascadeType.ALL,fetch=FetchType.LAZY) //it means one author for many books
    @JsonIgnoreProperties("author")//whenever you are trying to fetch the author fetch the author but  don't fetch the book again
    private List<Book> booksWritten; // and author contains book in itself so there is stack overflow like condition here both calling each other

    public Author()
    {}

    public Author( String name, String email, int age, String country) {

        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Book> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(List<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }
}
