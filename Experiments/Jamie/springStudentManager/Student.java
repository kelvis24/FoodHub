package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Name", nullable=false)
	private String name;
	
	@Column(name="Email", nullable=false)
	private String email;
	
	@Column(name="Age", nullable=true)
	private int age;
	
	@Column(name="Year", nullable=false)
	private int year;
	
	@Column(name="Grade", nullable=true)
	private char grade;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public Student(Long id, String name, String email, int age, int year, char grade) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.year = year;
		this.grade = grade;
	}
	public Student() {
		
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
}
