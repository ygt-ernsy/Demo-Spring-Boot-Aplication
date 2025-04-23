package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {

	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")

	private long id;
	private String name;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	@Transient
	private Integer age;

	public Student() {

	}

	// with id
	public Student(long id, String name, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	// without id
	public Student(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public String getEmail() {
		return this.email;
	}

	public LocalDate getDob() {
		return this.dob;
	}

	@Override
	public String toString() {
		return "Student{id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", dob=" + dob +
				'}';
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
