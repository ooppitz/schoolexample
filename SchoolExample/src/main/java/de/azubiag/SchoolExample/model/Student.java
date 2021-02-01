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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDSTUDENT;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@ManyToMany
	@JoinTable(name = "jo_student_course", joinColumns = @JoinColumn(name = "fk_studentid"), inverseJoinColumns = @JoinColumn(name = "fk_courseid"))
	Set<Course> courses = new HashSet<>();

	/** Default constructor for use by Hibernate */
	public Student() {
		super();
	}

	/** Create a student without an assigned course. */
	public Student(String fname, String lname) {
		this();
		this.firstname = fname;
		this.lastname = lname;
	}

	/**
	 * Create a student object with an assigned course.
	 * 
	 * @param fname first name
	 * @param lname last name
	 */
	public Student(String fname, String lname, Course c) {
		this(fname, lname);
		this.courses.add(c);
	}

	/**
	 * Add a course to the student's courses
	 * 
	 * @param course to add
	 */
	public void add(Course course) {
		this.courses.add(course);
		course.add(this);
	}

	/**
	 * Remove the student from all courses he attends.
	 */
	public void prepareToRemove() {

		Set<Course> courseList = this.courses;
		for (Course c : courseList) {
			c.remove(this);
		}
	}

	/**
	 * Remove a course from the list of attended courses
	 * 
	 * @param course to remove
	 */
	public void remove(Course course) {
		this.courses.remove(course);
	}

	public String getFname() {
		return firstname;
	}

	public void setFname(String fname) {
		this.firstname = fname;
	}

	public String getLname() {
		return lastname;
	}

	public void setLname(String lname) {
		this.lastname = lname;
	}

	@Override
	public String toString() {
		String r = this.getFname() + " " + this.getLname() + " (";
		for (Course c : this.courses) {
			r += c.getName() + " ";
		}
		r += ")";
		return r;
	}

	/** Prints all the students including the details */
	public static void printTable(EntityManager em) {

		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT s FROM Student s WHERE id IS NOT NULL";
			TypedQuery<Student> query = em.createQuery(queryString, Student.class);
			List<Student> list = query.getResultList();
			for (Student s : list) {
				System.out.println(s);
			}
			System.out.println();

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
	}
}