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

import de.azubiag.SchoolExample.app.SchoolDBApp;

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

	public void removeAllStudents() {

		this.students = new HashSet<Student>();

	}

	/**
	 * Add a student to the course
	 * 
	 * @param student to add
	 */
	public void add(Student student) {
		this.students.add(student);
	}

}
