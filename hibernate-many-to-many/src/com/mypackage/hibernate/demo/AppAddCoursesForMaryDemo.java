package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Course;
import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;
import com.mypackage.hibernate.entity.Review;
import com.mypackage.hibernate.entity.Student;



public class AppAddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get the student mary from database
			int id=2;
			Student student = session.get(Student.class, id);
			System.out.println("Student is: "+student);
			System.out.println("Courses: "+student.getCourses());
			
			
			//create more courses
			Course course1 = new Course("Rafting: Beginner");
			Course course2 = new Course("Rafting: Advanced");
			
			//add student to courses
			course1.addStudent(student);
			course2.addStudent(student);
			
			//save the courses
			System.out.println("Saving the courses");
			session.save(course1);
			session.save(course2);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
