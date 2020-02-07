package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Course;
import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;
import com.mypackage.hibernate.entity.Review;



public class AppDeleteCourseAndReviewDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
		
			//get the course
			int id = 11;
			Course theCourse = session.get(Course.class, id);
			
			//print the course
			System.out.println("Course is: "+theCourse);
			
			//print the reviews
			System.out.println("Reviews are: "+theCourse.getReviews());
			
			//delete the course
			session.delete(theCourse);
			
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
