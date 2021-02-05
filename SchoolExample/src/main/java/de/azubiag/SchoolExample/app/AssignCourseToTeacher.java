package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Teacher;

public class AssignCourseToTeacher {

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			// Find the student
			String teacherQueryStr = "SELECT t FROM Teacher t WHERE t.lastname = 'Mandorfer'";
			TypedQuery<Teacher> teacherQuery = em.createQuery(teacherQueryStr, Teacher.class);
			Teacher teacher = teacherQuery.getSingleResult();
			
			// Find the course
			String courseQueryStr = "SELECT c FROM Course c WHERE name = 'Chormusik'";
			TypedQuery<Course> courseQuery = em.createQuery(courseQueryStr, Course.class);
			Course course = courseQuery.getSingleResult();
			
			System.out.println("*** Adding for teacher " + teacher + " course " + course.getName()  + "\n");
			teacher.assign(course);
			
			System.out.println("Course: " + course);

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		
		System.out.println();
		System.out.println("=== Teachers =======================");
		Teacher.printTable();

	}

}
