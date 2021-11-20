package com.example.liberary.studentliberary.Services;

import com.example.liberary.studentliberary.Model.*;
import com.example.liberary.studentliberary.Repository.BookRepository;
import com.example.liberary.studentliberary.Repository.CardRepository;
import com.example.liberary.studentliberary.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService
{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${books.max_allowed}")
    int max_allowed_books;

    @Value("${books.max_allowed_days}")
    int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    int fine_per_day;





    public  String issueBook(int cardId,int bookId) throws Exception
    {
      Book book= bookRepository.findById(bookId).get();
      Card card=cardRepository.findById(cardId).get();
        Transaction transaction=new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);

      if(book ==null ||  !book.isAvailable())
      {
          transaction.setTransactionStatus(TransactionStatus.FAILED);
          throw  new Exception("book is unavailable or not present");

      }
      if(card==null || card.getCardStatus().equals(CardStatus.DEACTIVATED))
      {
          transaction.setTransactionStatus(TransactionStatus.FAILED);
          throw new Exception("Card is invalid");
      }
      if(card.getBooks().size()>=max_allowed_books)
      {
          transaction.setTransactionStatus(TransactionStatus.FAILED);
          throw new Exception("the book limit has reached for this card");
      }
      book.setCard(card);
      List<Book> bookList=card.getBooks();
      bookList.add(book);
      card.setBooks(bookList);

      bookRepository.updateBook(book);


        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
       transactionRepository.save(transaction);


        return transaction.getTransactionId();



    }
    public String returnBook(int cardId,int bookId) throws Exception
    {
       List<Transaction> transactions = transactionRepository.find(cardId, bookId,TransactionStatus.SUCCESSFUL,true);

        Transaction transaction=  transactions.get(transactions.size()-1);
        Date issueDate= transaction.getTransactionDate();
        long timeIssueTime=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long no_of_days_passed= TimeUnit.DAYS.convert(timeIssueTime,TimeUnit.MILLISECONDS);

        int fine=0;
        if(no_of_days_passed>getMax_allowed_days)
        {
            fine=(int)((no_of_days_passed-getMax_allowed_days) * fine_per_day);

        }
        Book book=transaction.getBook();
        book.setAvailable(true);
        book.setCard(null);

        bookRepository.updateBook(book);


        transaction.getBook().setCard(null);

        Transaction tr=new Transaction();
        tr.setBook(transaction.getBook());
        tr.setCard(transaction.getCard());
        tr.setIssueOperation(false);
        tr.setFineAmount(fine);
        tr.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        transactionRepository.save(tr);
        return tr.getTransactionId();

    }


}
