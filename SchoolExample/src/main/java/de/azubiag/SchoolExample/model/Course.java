package de.azubiag.SchoolExample.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCourse;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "courses")
	protected Set<Student> students = new HashSet<Student>();

	/** Default constructor required by hibernate */
	public Course() {
		super();
	}

	/**
	 * Create Course object.
	 * 
	 * @param name of the course
	 */
	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	
	/**
	 * Add a student to the course
	 * 
	 * @param student to add
	 */
	public void add(Student student) {
		this.students.add(student);
	}
	
	public void remove(Student student) {
		this.students.remove(student);
	}
	
	/**
	 * Removes references that are kept in the join table. This is required before
	 * being able to remove the object
	 */
	public void prepareToRemove() {

		// From the still attending students remove the reference to the soon invalid
		// course

		System.out.println("Removing all Students from course " + this.getName() + ":");

		Set<Student> studentList = this.getStudents();
		for (Student s : studentList) {
			System.out.println("Removing " + s.getFname() + " " + s.getLname());
			s.remove(this);
		}

		// Remove the references to attending students
		this.students = null;

	}

	/** Overrides the standard toString() method */
	@Override
	public String toString() {
		
		String result = "";

		result += "--- Course '" + this.getName() + "':\n";
		Set<Student> students = this.getStudents();
		for (Student s : students) {
			result += "- Student : " + s + "\n";
		}
		result += "\n";
		return result;
	}

	/** Prints all the courses including the details */
	public static void printTable(EntityManager em) {
		
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT c FROM Course c WHERE id IS NOT NULL";
			TypedQuery<Course> query = em.createQuery(queryString, Course.class);
			List<Course> list = query.getResultList();
			
			for (Course c : list) {
				System.out.print(c);
			}
			
			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
	}

}
