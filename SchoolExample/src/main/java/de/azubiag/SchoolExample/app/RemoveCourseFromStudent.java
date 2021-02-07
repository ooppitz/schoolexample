package de.azubiag.SchoolExample.app;

import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;

public class RemoveCourseFromStudent {

	public static void main(String[] args) {

		EntityTransaction et = Model.em.getTransaction();
		et.begin();

		try {

			// Find the course
			Course course = Course.find("Mathematik");
			System.out.println("Course: " + course.getName());

			// Find the student
			Student student = Student.find(20); // Christian Radhuber			
			System.out.println("Student: " + student);

			
			System.out.println("*** Removing course " + course + " from student " + student);
			student.remove(course);

			System.out.println("Student: " + student);

		} catch (Exception e) {

			e.printStackTrace();
			
		}

		et.rollback();
	}

}
