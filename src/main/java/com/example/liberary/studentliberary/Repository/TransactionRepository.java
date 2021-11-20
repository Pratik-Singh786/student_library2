package com.example.liberary.studentliberary.Repository;

import com.example.liberary.studentliberary.Model.Transaction;
import com.example.liberary.studentliberary.Model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface TransactionRepository  extends JpaRepository<Transaction,Integer>
{
    //you can sort on the basis of date to get the latest issual date .
    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.isIssueOperation=:isIssue and t.transactionStatus=:status")
    public List<Transaction> find(int cardId, int bookId, TransactionStatus status, boolean isIssue);

}
