package org.example.controller;

import org.example.hibernate_utils.HibernateUtils;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentController {
    Scanner sc=new Scanner(System.in);
    Student student=new Student();
    Session session= HibernateUtils.getSessionFactory().openSession();
    Transaction transaction=null;
   public void addStudents(){
        System.out.println("Enter the student name: ");
        String name=sc.next();
        System.out.println("Enter the student email: ");
        String email=sc.next();
        System.out.println("Enter the student password: ");
        String password=sc.next();
        try{
            student.setName(name);
            student.setEmail(email);
            student.setPassword(password);
            transaction= session.beginTransaction();
            session.save(student);
            transaction.commit();
            System.out.println("Student created successfull.");
        }catch (Exception e){
           e.printStackTrace();
           transaction.rollback();
        }finally {
            session.close();
        }
    }

    public void getStudentById(){
        System.out.println("Enter the student id to show student: ");
        int id=sc.nextInt();
        try{
            Student getStudent=session.get(Student.class,id);
            if(getStudent== null){
                System.out.println("Student not exist in the database");
            }else{
                System.out.println(getStudent.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void getAllStudents(){
       try{
           List<Student> students=session.createQuery("FROM Student", Student.class).getResultList();
           ArrayList<Student> list=new ArrayList<>(students);
           if(!list.isEmpty()){
               Iterator i=list.iterator();
               while (i.hasNext()){
                   System.out.println(i.next());
               }
           }else {
               System.out.println("List is empty.");
           }
       }catch (Exception e){

       }finally {
           session.close();
       }
    }

    public  void updateStudent(){
       try{
           System.out.println("Enter the id to update the student student ");
           int id=sc.nextInt();

           Student findStudent=session.get(Student.class,id);
           if(findStudent !=null){
               System.out.println("Enter the student name: ");
               String name=sc.next();
               System.out.println("Enter the student email: ");
               String email=sc.next();
               System.out.println("Enter the student password: ");
               String password=sc.next();

               findStudent.setName(name);
               findStudent.setEmail(email);
               findStudent.setPassword(password);

               transaction=session.beginTransaction();
               session.update(findStudent);
               System.out.println("Student update successfully...");
               transaction.commit();
           }else{
               System.out.println("Student not exist in database");
           }

       }catch (Exception e){
           e.printStackTrace();
           transaction.rollback();
       }finally {
           session.close();
       }
    }

    public void deleteStudent(){
       try{
           System.out.println("Enter the student id to delete: ");
           int id=sc.nextInt();
           Student getStud=session.get(Student.class,id);
           if(getStud!=null){
               transaction=session.beginTransaction();
               session.delete(getStud);
               transaction.commit();
           }else{
               System.out.println("Student not exist in database.");
           }
       }catch (Exception e){
           e.printStackTrace();
           transaction.rollback();
       }finally {
           session.close();
       }
    }

}
