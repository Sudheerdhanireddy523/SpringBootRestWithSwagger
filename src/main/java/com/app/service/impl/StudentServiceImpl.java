package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Student;
import com.app.repo.StudentRepository;
import com.app.service.IStudentService;
@Service
public class StudentServiceImpl implements IStudentService{
	@Autowired
	private StudentRepository repo;

	@Transactional
	public Integer saveStudent(Student s) {
		return repo.save(s).getStdId();
		// TODO Auto-generated method stub
		
	}

   @Transactional(readOnly=true)
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Transactional(readOnly = true)
public Student getOneStudent(Integer id) {
	// TODO Auto-generated method stub
	return repo.findById(id).get();
}

	@Transactional
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		
		 repo.deleteById(id);
	}

	@Transactional
	public boolean updateStudent(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	
}
