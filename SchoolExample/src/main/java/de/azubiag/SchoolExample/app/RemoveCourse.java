package de.azubiag.SchoolExample.app;

import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class RemoveCourse {

	public static void main(String[] args) {

		String courseName = "Politische Bildung";

		EntityTransaction et = Model.em.getTransaction();
		et.begin();
		
		try {
			System.out.println("=== Courses before transaction ============================");
			Course.printTable();

			System.out.println("*** Create course " + courseName);
			createCourse(courseName);
			System.out.println();
			
			System.out.println("*** Found course " + Course.find(courseName));
			System.out.println();


			System.out.println("*** Delete course " + courseName);
			deleteCourse(courseName);

			System.out.println("=== Courses after deletion ==============================");
			Course.printTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		et.rollback();

	}

	/** Creates a course and assigns to existing students to it */
	private static void createCourse(String courseName) {

		Course c = new Course(courseName);

		Teacher tid20 = Teacher.find(20); // Hans Achleitner
		Student sid20 = Student.find(20); // Christian Radhuber
		Student sid21 = Student.find(21); // Peter Mali

		c.assign(tid20);
		c.assign(sid20);
		c.assign(sid21);

		Model.em.persist(c);
	
	}

	private static void deleteCourse(String courseName) {

		/*
		 * String queryString = "SELECT c from Course c WHERE c.name='" + courseName +
		 * "'"; TypedQuery<Course> query = em.createQuery(queryString, Course.class);
		 * List<Course> courseList = query.getResultList(); Course course =
		 * courseList.get(0); course.prepareToRemove(); em.remove(course);
		 */

		Course course = Course.find(courseName);

		System.out.println();
		System.out.println("*** Removing course " + courseName);

		course.remove();
		System.out.println();

	}

}
