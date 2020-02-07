package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Course;
import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;
import com.mypackage.hibernate.entity.Review;



public class AppCreateCourseAndReviewDemo {

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
		
			//create a course
			Course course = new Course("Rafting- Intermediate");
			
			//add some reviews
			course.addReview(new Review("Great Course, loved it"));
			course.addReview(new Review("Easy to follow"));
			course.addReview(new Review("Well done, amazing course"));
			course.addReview(new Review("Great for beginners"));
			
			//save the course.. and reviews coz of cascading
			System.out.println("Saving the course");
			System.out.println(course);
			System.out.println(course.getReviews());
			session.save(course);
			
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
