package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class ComplexUseCase {


	public static void main(String[] args) {

		EntityManager em = Model.em;

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
