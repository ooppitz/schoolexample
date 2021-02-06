package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class ComplexUseCase {

	public static void main(String[] args) {

		// TODO: Extend
		// - create some course, teachers, students and connect them
		// - add the objects to the DB
		// - remove the objects from the DB

		EntityTransaction et = Model.em.getTransaction();
		et.begin();

		String courseName = "Religion";
		String teacherFirstname = "Nikolaus";
		String teacherLastname = "Zacherl";

		String courseName2 = "Stenographie";
		String teacherFirstname2 = "Manfred";
		String teacherLastname2 = "Weigerstorfer";
		try {

			// Create a course, a teacher and link them
			createCourseWithTeacherAndStudents(courseName, teacherFirstname, teacherLastname);
			createCourseWithTeacherAndStudents(courseName2, teacherFirstname2, teacherLastname2);

			{
				Course c = Course.find(courseName);
				Course c2 = Course.find(courseName2);

				System.out.println();
				System.out.println("Course 1 = " + c);
				System.out.println("Course 2 = " + c2);
				System.out.println();
			}

			deleteTeacher(teacherFirstname, teacherLastname); // Died of heart attack
			deleteCourse(courseName2); // the class graduated
			deleteStudent(21);  // Expelled from school
			
			{
				Course c = Course.find(courseName);
				Course c2 = Course.find(courseName2);

				System.out.println();
				System.out.println("Course 1 = " + c);
				System.out.println("Course 2 = " + c2);
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		et.rollback();

	}

	private static void deleteCourse(String courseName) {

		Course c = Course.find(courseName);
		if (c != null) {
			System.out.println();
			System.out.println("*** Deleting course " + courseName);
			System.out.println();
			c.remove();
		}
	}

	private static void deleteTeacher(String teacherFirstname, String teacherLastname) {

		Teacher t = Teacher.find(teacherFirstname, teacherLastname);
		if (t != null) {
			System.out.println();
			System.out.println("*** Deleting teacher " + t.getName());
			System.out.println();
			t.remove();
		}
	}
	
	private static void deleteStudent(int id) {

		Student s = Student.find(id);
		if (s != null) {
			System.out.println();
			System.out.println("*** Deleting student " + s.getName());
			System.out.println();
			s.remove();
		}
	}

	/**
	 * Creates a course, a teacher and links both
	 * 
	 */
	private static void createCourseWithTeacherAndStudents(String courseName, String teacherFirstname, String teacherLastName) {

		Course c = new Course(courseName);
		Teacher t = new Teacher(teacherFirstname, teacherLastName);

		Student s1 = Student.find(20);
		Student s2 = Student.find(21);
		Student s3 = Student.find(22);
		c.assign(s1);
		c.assign(s2);
		c.assign(s3);
		
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
