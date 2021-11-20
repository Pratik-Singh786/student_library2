package com.example.liberary.studentliberary.Repository;

import com.example.liberary.studentliberary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer>
{



/*
   @Query("select b from Book b where b.available =:availabilty and b.author in(select a from Author a  where a.name =:author_name)")
    List<Book> findBooksByAuthor(String  author_name,boolean availabilty);

    @Query("select b from Book b where b.genre =:genre and b.available =:available ")
    List<Book> findBooksByGenre(String genre,boolean available);

    @Query("select b from Book b where b.available =:available and b.genre =:genre and b.author in(select a from Author a  where a.name =:author_name)")
    List<Book> findBooksByGenreAuthor(String genre, String author,boolean available);



    @Query(value ="select b from Book b where b.available =:availabilty",nativeQuery = true)
    List<Book> findByAvailability(boolean availabilty);

    @Modifying
    @Transactional
    @Query("update Book b set b.available =:#{#book.available},b.card =:#{#book.card} where b.id =:#{#book.id}")
    int updateBook(Book book);//it is for transaction
*/
@Query("select b from Book b where b.available =:availabilty and b.author in (select a from Author a where a.name =:author_name)")
List<Book> findBooksByAuthor(String author_name, boolean availabilty);

 @Query("select b from Book b where b.genre =:genre and b.available =:available")//jpql query
 List<Book> findBooksByGenre(String genre, boolean available);

 @Query("select b from Book b where b.available =:available and b.genre =:genre and b.author in (select a from Author a where a.name =:author_name)")
 List<Book> findBooksByGenreAuthor(String genre, String author, boolean available);

 @Query(value = "select * from book b where b.available =:availabilty", nativeQuery = true)//sql query table name should be small.
 List<Book> findByAvailability(boolean availabilty);//if we are using the custom query it will fetch in eager way.


 @Modifying
 @Transactional
 @Query("update Book b set b.available =:#{#book.available}, b.card =:#{#book.card} where b.id =:#{#book.id}")
 int updateBook(Book book);




}
