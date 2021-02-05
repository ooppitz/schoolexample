package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class ComplexUseCase {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		System.out.println("=== Courses ===================");
		Course.printTable();

		System.out.println("=== Teachers ===================");
		Teacher.printTable();

		System.out.println("=== Students ===================");
		Student.printTable();

		// Assign Mathematik to Hans Achleitner

		Course c = Course.find("Mathematik");
		Teacher t = Teacher.find("Hans", "Achleitner");

		if (c != null && t != null) {
			c.setTeacher(t);
		}

	}

}
