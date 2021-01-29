package de.azubiag.SchoolExample.app;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;

public class AssignCourseToStudent {

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			// Find the course
			String courseQueryStr = "SELECT c FROM Course c WHERE name = 'Griechisch'";
			TypedQuery<Course> courseQuery = em.createQuery(courseQueryStr, Course.class);
			Course course = courseQuery.getSingleResult();
			System.out.println("Course: " + course.getName());

			// Find the student
			String studentQueryStr = "SELECT s FROM Student s WHERE fname = 'Roswita' AND lname = 'Röll'";
			TypedQuery<Student> studentQuery = em.createQuery(studentQueryStr, Student.class);
			Student student = studentQuery.getSingleResult();
			System.out.println("Student: " + student.getFname() + " " + student.getLname());

			student.add(course);

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}

	}

}
