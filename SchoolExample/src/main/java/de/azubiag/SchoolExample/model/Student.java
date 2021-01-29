package de.azubiag.SchoolExample.model;

import java.util.HashSet;
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

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDSTUDENT;

	@Column(name = "firstname")
	private String fname;
	
	@Column(name = "lastname")
	private String lname;

	@ManyToMany
	@JoinTable(name = "jo_student_course", 
		joinColumns = @JoinColumn(name = "fk_studentid"), 
		inverseJoinColumns = @JoinColumn(name = "fk_courseid"))
	Set<Course> courses = new HashSet<>();

	/** Default constructor for use by Hibernate */
	public Student() {
		super();
	}
	
	/** Create a student without an assigned course. */
	public Student(String fname, String lname) {
		this();
		this.fname = fname;
		this.lname = lname;
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
	

	/** Add a course to the student's courses
	 * 
	 * @param course to add
	 */
	public void add(Course course) {
		this.courses.add(course);
	}


	/** Remove course
	 * 
	 * @param course to remove
	 */
	public void remove(Course course) {
		this.courses.remove(course);
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	
}