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

import com.app.model.Customer;
import com.app.service.ICustomerService;

@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController {
	
	@Autowired
	private ICustomerService service;
	
	@PostMapping("/insert")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
		
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveCustomer(customer);
			resp=new ResponseEntity<String>("customer '"+id+"' created", HttpStatus.OK);
			
		}catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCustomers(){
		ResponseEntity<?> resp=null;
		try {
			List<Customer> list=service.getAllCustomers();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
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
	
	public ResponseEntity<?> getOneCustomer(@PathVariable("id") Integer cid){
		ResponseEntity<?> resp=null;
		try {
		Customer c=service.getOneCustomer(cid);
		if(c!=null)
			resp=new ResponseEntity<Customer>(c, HttpStatus.OK);
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
	public ResponseEntity<String> deleteOneCustomer(@PathVariable ("id") Integer cid){
		ResponseEntity<String> resp=null;
		try {
			service.deleteCustomer(cid);
			resp=new ResponseEntity<String>("deleted '"+cid+"' successfully", HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>("unable to delete", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
	}
   @PutMapping("/edit/{id}")
   public ResponseEntity<String> editData(@PathVariable ("id") Integer cid,@RequestBody Customer customer){
	   ResponseEntity<String> resp=null;
	
		
	if(service.updateCustomer(cid)) {
		   service.getOneCustomer(cid);
	service.saveCustomer(customer);
		   resp=new ResponseEntity<String>("Updated data successfully", HttpStatus.OK);
	
	} else {
	 
	   
		   resp=new ResponseEntity<String>("no id found", HttpStatus.BAD_REQUEST);
		   
		  
	}
	return resp;
   }


}
