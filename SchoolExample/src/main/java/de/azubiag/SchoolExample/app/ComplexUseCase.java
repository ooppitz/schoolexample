package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class ComplexUseCase {


	public static void main(String[] args) {

		EntityManager em = Model.ENTITY_MANAGER_FACTORY.createEntityManager();

		System.out.println();
		System.out.println("=== Courses ===================");
		Course.printTable();

		System.out.println();
		System.out.println("=== Teachers ===================");
		Teacher.printTable();

		System.out.println();
		System.out.println("=== Students ===================");
		Student.printTable();

		// Assign Mathematik to Hans Achleitner

		Course c = Course.find("Sport");
		Teacher t = Teacher.find("Karl-Heinz", "Meidinger");

		System.out.println(c);
		System.out.println(t);
		
		if (c != null && t != null) {

			System.out.println();
			System.out.println("*** Linking teacher " + t + " and Course " + c);
			System.out.println();
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			c.assign(t);
			
			em.merge(c);
			em.merge(t);
			
			et.commit();	
			
		}

		System.out.println();
		System.out.println("=== Courses ===================");
		Course.printTable();

		System.out.println();
		System.out.println("=== Teachers ===================");
		Teacher.printTable();
	}

}
