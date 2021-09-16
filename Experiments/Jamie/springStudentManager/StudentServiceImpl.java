package com.example.demo.service.impl;
import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepository;
import com.example.demo.service.StudentService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
}
