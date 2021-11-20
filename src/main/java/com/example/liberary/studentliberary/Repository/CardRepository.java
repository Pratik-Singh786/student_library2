package com.example.liberary.studentliberary.Repository;

import com.example.liberary.studentliberary.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CardRepository  extends JpaRepository<Card,Integer>
{
    @Modifying
    @Transactional
    //from here it will pick the student_id and related card_id and  deactivate the card  for the user
    @Query(value = "update card c set c.card_status =:status where c.id in (select card_id from student s where s.id =:student_id)", nativeQuery = true)
    void deactivateCard(int student_id, String status);
    //if you first delete the student_id you wont be able to fetch the card_id so first deactivate




}
