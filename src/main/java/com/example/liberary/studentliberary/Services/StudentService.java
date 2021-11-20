package com.example.liberary.studentliberary.Services;

import com.example.liberary.studentliberary.Model.Card;
import com.example.liberary.studentliberary.Model.Student;
import com.example.liberary.studentliberary.Repository.CardRepository;
import com.example.liberary.studentliberary.Repository.StudentRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    private static Logger logger= LoggerFactory.getLogger(StudentService.class);
    @Autowired
    CardService cardService;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CardRepository cardRepository;

    public Student getDetails(String email)
    {
       return  studentRepository.findByEmailId(email);
    }

    public Student getDetailsById(int id)
    {
        return studentRepository.findById(id);

    }


    public void createStudent(Student student)
    {
        Card newCard=cardService.createAndReturn(student);//to create student and  card both parallely;
        logger.info("the card for the student is created with the details {} "+student ,newCard);
    }

    public void updateStudent(Student student)
    {
       studentRepository.updateStudentDetails(student);

    }

    public void deleteStudent(int id) //the id which we have passed  in deleteStudent deactivateCard()will take it first
    {
        cardService.deactivateCard(id);
     studentRepository.deleteCustom(id);

    }





}
