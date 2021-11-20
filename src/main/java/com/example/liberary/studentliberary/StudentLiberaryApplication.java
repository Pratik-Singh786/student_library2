package com.example.liberary.studentliberary;

import com.example.liberary.studentliberary.Model.*;
import com.example.liberary.studentliberary.Repository.AuthorRepository;
import com.example.liberary.studentliberary.Repository.BookRepository;
import com.example.liberary.studentliberary.Repository.CardRepository;
import com.example.liberary.studentliberary.Repository.StudentRepository;
import com.example.liberary.studentliberary.security.AuthorityConstants;
import com.example.liberary.studentliberary.security.User;
import com.example.liberary.studentliberary.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class StudentLiberaryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentLiberaryApplication.class, args);
		System.out.println("hello!!");
	}

	@Autowired
//	static StudentRepository studentRepository;
//query did not return a unique result: 3; nested exception is javax.persistence  for these kind pf errors go to  user  table of security class and make sure that 	are there are only two unique users student,authority
	StudentRepository studentRepository;//we cant use static with autowired because static means current class context;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

/*	Now admin is already created and we don't a admin again
BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();  // it should be run only once bcz after that it will give 3. nested query exception
		User user1= User.builder()
				   .username("Jhon")
				   .password(encoder.encode("jhon123"))
				   .authority(AuthorityConstants.ADMIN_AUTHORITY)
				    .build();
		userRepository.save(user1);*/

		/*User user2= User.builder()
				.username("Jim")
				.password(encoder.encode("jim123"))
				.authority(AuthorityConstants.STUDENT_AUTHORITY)
				.build();



		userRepository.save(user1);
		userRepository.save(user2);*/



			/*1.Student student = new Student("pratik.rxl@gmail.com", "pratik", 20, "Sukovia");
			//Student student1=new Student("abc.rxl@gmail.com","ankit",20,"Wakanda");
			Card card = new Card();

			;// establishing bidirectional relationship between child and parent
		2.	student.setCard(card);
		3.	card.setStudent(student);

			//studentRepository.save(student); //saving student without saving the parent can give error ,suppose you go to college there is two tables,student and department student wants to take the admission in that department which does'nt exist;
		8	cardRepository.save(card);//saving it to parent Card,dont need to save student because card is bidirectional
		9	//studentRepository.save(student);
			//	cardRepository.findAll().stream().forEach(x->  System.out.println(x.getId()+" "+x.getStudent()));
			//  Card card1= cardRepository.findById(1).get();
			// System.out.println("id="+card1.getId());
			cardRepository.findAll().stream().forEach(System.out::println);
			//System.out.println(studentRepository.findStudentByEmailBySQl("pratik.rxl@gmail.com"));
		//	System.out.println(studentRepository.updateStudentEmail("pratik.rxl@gmail.com", "pratik123.rxl@gmail.com"))
			//	;"
		4.	Author author=new Author("william","william@gmail.com",20,"India");
		5.	Book book=new Book("Human Psychology", Genre.FICTIONAL,author);

		6.	authorRepository.save(author);
		7.	bookRepository.save(book);


		}*/
	}
}







