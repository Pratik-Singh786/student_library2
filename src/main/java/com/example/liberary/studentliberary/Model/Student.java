package com.example.liberary.studentliberary.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int id;
    @Column(unique = true)
    private String emailId;

   private String name;
   private int age;  //in case we want  to check on the basis of age
   // private String password;

   private String country;
    // @ManyToOne  //Many students are mapped to a card
   //@OnetoOne
    @ManyToOne
    @JoinColumn // in background @joinColumn is doing this join this column to the primary key of card table  Card is parent entity;
    @JsonIgnoreProperties("student")
    private Card card;
    //alter table  student add foreign key constraint card  refrences card(id)

    @CreationTimestamp
    private Date createOn;

    @UpdateTimestamp
    private Date updatedOn;


    public Student( String emailId, String name, int age, String country) {

        this.emailId = emailId;
        this.name = name;
        this.age = age;
        this.country = country;

    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

  /*  public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
              //  ", card=" + card +
                ", createOn=" + createOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}

