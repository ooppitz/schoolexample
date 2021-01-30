package de.azubiag.SchoolExample.app;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.util.Util;

public class AssignCourseToStudent {

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			// Find the course
			String courseQueryStr = "SELECT c FROM Course c WHERE name = 'Biology'";
			TypedQuery<Course> courseQuery = em.createQuery(courseQueryStr, Course.class);
			Course course = courseQuery.getSingleResult();
			
			System.out.println(course);

			// Find the student
			String studentQueryStr = "SELECT s FROM Student s WHERE fname = 'Leonhard' AND lname = 'Klinglmeier'";
			TypedQuery<Student> studentQuery = em.createQuery(studentQueryStr, Student.class);
			Student student = studentQuery.getSingleResult();

			student.add(course);
			course.add(student);
			
			System.out.println(course);

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		
		System.out.println("=== Courses =======================");
		Course.printTable(em);

	}

}
