package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
