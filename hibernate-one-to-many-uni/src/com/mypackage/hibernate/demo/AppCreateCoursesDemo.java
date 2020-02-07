package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Course;
import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;



public class AppCreateCoursesDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
		
			//get the instructor from db
			int id=1;
			Instructor theInstructor = session.get(Instructor.class, id);
			
			//create some courses
			Course course1 = new Course("Rafting Beginner");
			Course course2 = new Course("Rafting Intermediate");
			Course course3 = new Course("Rafting Advanced");
			
			//add courses to instructor
			theInstructor.add(course1);
			theInstructor.add(course2);
			theInstructor.add(course3);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
						
			// commit transaction
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
