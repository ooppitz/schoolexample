package de.azubiag.SchoolExample.app;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class QueryAllTables {

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

	}

}
