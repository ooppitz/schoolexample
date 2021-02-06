package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class ComplexUseCase {

	public static void main(String[] args) {

		System.out.println();
		System.out.println("=== Courses ===================");
		Course.printTable();

		System.out.println();
		System.out.println("=== Teachers ===================");
		Teacher.printTable();

		System.out.println();
		System.out.println("=== Students ===================");
		Student.printTable();

		// TODO: Extend
		// - create some course, teachers, students and connect them
		// - add the objects to the DB
		// - remove the objects from the DB

		EntityTransaction et = Model.em.getTransaction();
		et.begin();

		String courseName = "Religion";
		String teacherFirstname = "Nikolaus";
		String teacherLastname = "Zacherl";

		try {

			// Create a course, a teacher and link them
			createCourseWithTeacher(courseName, teacherFirstname, teacherLastname);

			Course c = Course.find(courseName);
			System.out.println();
			System.out.println("Course = " + c);
			System.out.println();

			// delete the teacher (should remove it from course)
			Teacher t = Teacher.find(teacherFirstname, teacherLastname);
			System.out.println("Teacher " + t);
			if (t != null) {
				System.out.println();
				System.out.println("*** Deleting teacher");
				System.out.println();
				t.remove();
			}

			c = Course.find(courseName);
			System.out.println();
			System.out.println("Course = " + c);
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("=== Courses ===================");
		Course.printTable();

		System.out.println();
		System.out.println("=== Teachers ===================");
		Teacher.printTable();

		et.rollback();

	}

	/**
	 * Creates a course, a teacher and links both
	 * 
	 */
	private static void createCourseWithTeacher(String courseName, String teacherFirstname, String teacherLastName) {

		Course c = new Course(courseName);
		Teacher t = new Teacher(teacherFirstname, teacherLastName);

		System.out.println();
		System.out.println("*** Objects before linking them");
		System.out.println(c);
		System.out.println(t);

		if (c != null && t != null) {

			System.out.println();
			System.out.println("*** Linking teacher " + t + " and Course " + c);
			System.out.println();

			c.assign(t);
			Model.em.persist(c);
			Model.em.persist(t);
		}

		System.out.println();
		System.out.println("*** Objects after linking them");
		System.out.println(c);
		System.out.println(t);
	}

}
