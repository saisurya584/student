package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Student;
import com.cg.repository.StudentRepository;



@RestController
public class StudentController {
	
//	@Autowired
	private StudentRepository  studentRepository;
	
	@Autowired
	public StudentController(StudentRepository studentRepository) {
		
		this.studentRepository=studentRepository;
	}

	@PostMapping("/save")
	public Student save(@RequestBody Student student) 
	{
		return studentRepository.save(student);
	}

}
