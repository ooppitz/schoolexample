package de.azubiag.SchoolExample.app;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.util.Util;

public class RemoveCourse {

	public static void main(String[] args) {

		String courseName = "Stenographie";

		System.out.println("=== Courses =============================================");
		Course.printTable();

		createCourse(courseName);

		System.out.println("=== Courses after creation ==============================");
		Course.printTable();

		deleteCourse(courseName);

		System.out.println("=== Courses after deletion ==============================");
		Course.printTable();

	}

	/** Creates a course and assigns to existing students to it */
	private static void createCourse(String courseName) {
		
		EntityTransaction et = Model.em.getTransaction();
		try {

			et.begin();
			Course c = new Course(courseName);
			
			Student id20 = Model.em.merge(Student.find(20));
			Student id21 = Model.em.merge(Student.find(21));
			
			c.assign(id20);
			c.assign(id21);
			
			Model.em.persist(c);
			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
	}
	
	
	private static void deleteCourse(String courseName) {	
		
		EntityTransaction et = Model.em.getTransaction();
		
		try {
			et.begin();

			/*
			String queryString = "SELECT c from Course c WHERE c.name='" + courseName + "'";
			TypedQuery<Course> query = em.createQuery(queryString, Course.class);
			List<Course> courseList = query.getResultList();
			Course course = courseList.get(0);
			course.prepareToRemove();
			em.remove(course);
			*/
			
			Course detachedCourse = Course.find(courseName);
			
			System.out.println();
			System.out.println("*** Removing course " + courseName);
			Course attachedCourse = Model.em.merge(detachedCourse); // Attach instance
			attachedCourse.prepareToRemove();
			Model.em.remove(attachedCourse);
			System.out.println();

			et.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
