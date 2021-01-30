package de.azubiag.SchoolExample.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;

public class Util {

	
	public static void listAllStudents(EntityManager em) {
		
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT s FROM Student s WHERE id IS NOT NULL";
			TypedQuery<Student> query = em.createQuery(queryString, Student.class);

			List<Student> list = query.getResultList();

			System.out.println();
			System.out.println("--Students--------------------------------");
			
			for (Student s : list) {
				System.out.println(s.getFname() + " " + s.getLname());
			}

			System.out.println()
			;
			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
	}
	
	
	public static void listAllCourses(EntityManager em) {
		
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT c FROM Course c WHERE id IS NOT NULL";
			TypedQuery<Course> query = em.createQuery(queryString, Course.class);

			List<Course> list = query.getResultList();

			System.out.println();
			System.out.println("--Courses--------------------------------");
			
			for (Course c : list) {
				System.out.println(c.getName());
			}

			System.out.println()
			;
			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
	}
	
}
