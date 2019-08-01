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

import com.app.model.Employee;
import com.app.service.IEmployeeService;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeRestController {
	
	@Autowired
	private IEmployeeService service;
	
	@PostMapping("/insert")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
		
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveEmployee(employee);
			resp=new ResponseEntity<String>("employee '"+id+"' created", HttpStatus.OK);
			
		}catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees(){
		ResponseEntity<?> resp=null;
		try {
			List<Employee> list=service.getAllEmployees();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
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
	
	public ResponseEntity<?> getOneEmployee(@PathVariable("id") Integer eid){
		ResponseEntity<?> resp=null;
		try {
		Employee e=service.getOneEmployee(eid);
		if(e!=null)
			resp=new ResponseEntity<Employee>(e, HttpStatus.OK);
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
	public ResponseEntity<String> deleteOneEmployee(@PathVariable ("id") Integer eid){
		ResponseEntity<String> resp=null;
		try {
			service.deleteEmployee(eid);
			resp=new ResponseEntity<String>("deleted '"+eid+"' successfully", HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>("unable to delete", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
	}
   @PutMapping("/edit/{id}")
   public ResponseEntity<String> editData(@PathVariable ("id") Integer eid,@RequestBody Employee employee){
	   ResponseEntity<String> resp=null;
	
		
	if(service.updateEmployee(eid)) {
		   service.getOneEmployee(eid);
	service.saveEmployee(employee);
		   resp=new ResponseEntity<String>("Updated data successfully", HttpStatus.OK);
	
	} else {
	 
	   
		   resp=new ResponseEntity<String>("no id found", HttpStatus.BAD_REQUEST);
		   
		  
	}
	return resp;
   }


}
