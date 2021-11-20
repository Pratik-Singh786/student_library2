package com.example.liberary.studentliberary.Controller;

import com.example.liberary.studentliberary.Model.Student;
import com.example.liberary.studentliberary.Services.StudentService;
import com.example.liberary.studentliberary.security.AuthorityConstants;
import com.example.liberary.studentliberary.security.User;
import com.example.liberary.studentliberary.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController

{
    @Autowired
    StudentService studentService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity getAllStudents()
    {
        return new ResponseEntity<>("the list of all students is"+null,HttpStatus.OK);//it means fetching the details  of all the students so it's need admin permission
    }

    //getting details by emailId using as a user name;

    @GetMapping("/")
    public ResponseEntity getStudent()
    {
        //it holds username and password
        User principal= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//for better understanding put a debug point and run the application
       String username= principal.getUsername();

        Student obj= studentService.getDetails(username);
        return new ResponseEntity<>("the  students is"+obj,HttpStatus.OK); //it means fetching only one student i.e a student is fetching his details only.
    }

    //admin can get the details of a student by id - config(admin)
    @GetMapping("/studentById")
    public ResponseEntity getStudentById(@RequestParam("id")int id)
    {
        Student obj= studentService.getDetailsById(id);
        return new ResponseEntity<>("Student details -"+obj,HttpStatus.OK);

    }







   @PostMapping("/")
   public ResponseEntity createStudent(@RequestBody Student student)
   {
      // BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
     //  String encodedPassword=encoder.encode(student.getPassword());

       studentService.createStudent(student);
       BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

       User user=User.builder()
               .username(student.getEmailId())
               .password(encoder.encode("pass1234"))
               .authority(AuthorityConstants.STUDENT_AUTHORITY)
               .build();
       userRepository.save(user);
       return new ResponseEntity<>("the  student is successfully added to the system", HttpStatus.CREATED);
   }

   @PutMapping("/")
    public ResponseEntity updateStudent(@RequestBody Student student)
   {
       studentService.updateStudent(student); //while updating the student you have to pass all the fields what is the new value will be reflected as new
       return new  ResponseEntity<>("student is updated ",HttpStatus.ACCEPTED);
   }

   @DeleteMapping("/")
    public ResponseEntity deleteStudent(@RequestParam("id")int id)
   {
        studentService.deleteStudent(id);
       return new  ResponseEntity<>("student is deleted ",HttpStatus.ACCEPTED);
   }

   //update password authority should be only available to the student
   @PutMapping("/update_password")
    public  ResponseEntity updatePassword(@RequestParam("new_password")String new_password)
   {
       Principal principal= (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String username= principal.getName();
       BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
       String new_encoded_password= encoder.encode(new_password);
       //userRepository.update() write update() in UserRepository of security package
       return null;


   }





}
