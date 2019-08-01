package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/rest/student")
public class StudentRestController {
	@Autowired
	private IStudentService service;
	
	@PostMapping("/insert")
	public ResponseEntity<String> saveStudent(@RequestBody Student student){
		
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveStudent(student);
			resp=new ResponseEntity<String>("student '"+id+"' created", HttpStatus.OK);
			
		}catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents(){
		ResponseEntity<?> resp=null;
		try {
			List<Student> list=service.getAllStudents();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Student>>(list, HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("No data found", HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
	}
	
	@GetMapping("/one/{id}")
	
	public ResponseEntity<?> getOneStudent(@PathVariable("id") Integer sid){
		ResponseEntity<?> resp=null;
		try {
		Student std=service.getOneStudent(sid);
		if(std!=null)
			resp=new ResponseEntity<Student>(std, HttpStatus.OK);
		else
			resp=new ResponseEntity<String>("No data found", HttpStatus.BAD_REQUEST);
			
	}catch (Exception e) {
		// TODO: handle exception
		resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
	}
		return resp;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneStudent(@PathVariable ("id") Integer sid){
		ResponseEntity<String> resp=null;
		try {
			service.deleteStudent(sid);
			resp=new ResponseEntity<String>("deleted '"+sid+"' successfully", HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>("unable to delete", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
	}
   @PutMapping("/edit/{id}")
   public ResponseEntity<String> editData(@PathVariable ("id") Integer sid,@RequestBody Student student){
	   ResponseEntity<String> resp=null;
	
		
	if(service.updateStudent(sid)) {
		   service.getOneStudent(sid);
	service.saveStudent(student);
		   resp=new ResponseEntity<String>("Updated data successfully", HttpStatus.OK);
	
	} else {
	 
	   
		   resp=new ResponseEntity<String>("no id found", HttpStatus.BAD_REQUEST);
		   
		  
	}
	return resp;
   }
}
