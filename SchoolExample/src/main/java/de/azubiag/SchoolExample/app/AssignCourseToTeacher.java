package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Teacher;

public class AssignCourseToTeacher {

	public static void main(String[] args) {

		EntityManager em = Model.em;
		EntityTransaction et = em.getTransaction();
		et.begin();

		try {

			// Find the student
			String teacherQueryStr = "SELECT t FROM Teacher t WHERE t.firstname ='Ingrid'";
			TypedQuery<Teacher> teacherQuery = em.createQuery(teacherQueryStr, Teacher.class);
			Teacher teacher = teacherQuery.getSingleResult();

			Course course = Course.find("Musik");

			course.assign(Student.find(20));
			course.assign(Student.find(21));
			course.assign(Student.find(22));
			course.assign(Student.find(23));

			System.out.println("*** Adding for teacher " + teacher.getName() + " course " + course.getName() + "\n");
			teacher.assign(course);

			System.out.println("Course: " + course);
			System.out.println("Teacher: " + teacher);

		} catch (Exception e) {

			e.printStackTrace();

		}
		et.rollback();

	}
	
}
