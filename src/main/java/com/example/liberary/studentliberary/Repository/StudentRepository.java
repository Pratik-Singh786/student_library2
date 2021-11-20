package com.example.liberary.studentliberary.Repository;

import com.example.liberary.studentliberary.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface StudentRepository  extends JpaRepository<Student,Integer>
{
    //jpql= java Persistence Query language
    //Native Sql query
   @Modifying
   @Query("update Student s set s.emailId = :#{#std.emailId}, "+
           "s.name = :#{#std.name}, "+
           "s.age = :#{#std.age}, "+
           "s.country = :#{#std.country} "+
           "where s.id = :#{#std.id}")
   int updateStudentDetails(Student std); //whenever the function invoke,invoke this query

  /* @Modifying
    @Query("update Student s set s.emailId = :#{#std.emailId}, " +
            "s.name = :#{#std.name}, " +
            "s.age = :#{#std.age} ," +
            "s.country = :#{#std.country} " +
            "where s.id = :#{#std.id}")
    int updateStudentDetails(Student std);*/

    @Modifying
    @Query("delete from Student s where s.id = :id")
     void deleteCustom(int id) ;

    @Query("")
    Student findByEmailId(String email );

    @Query("")
    Student findById(int id );


   /* @Query("update Student s set s.emailId= :email where  s.id= :id" ) //this is a bad design instead of writing for every property write only one query but
    int updateStudentDetails(String email,int id);//update only fields which are required*/



  // @Query("select  b from Student b where  b.emailId =:my_email")  //emailId is property and my_email is argument jpql
 // public List<Student> findStudentByName(String my_email);
 // @Query(value = "select * from  Student s where s.email_id =:email",nativeQuery=true)
//  public List<Student> findStudentByEmailBySQl(String email);

 // @Transactional basically its taking lock over the query, so that query will execute in 1 go.it is a class level annotation so we can use it on the class
  // @Modifying
 //  @Query("update Student s set s.emailId =:newEmail where s.emailId =:oldEmail")
  // public int updateStudentEmail(String oldEmail,String newEmail);
}
