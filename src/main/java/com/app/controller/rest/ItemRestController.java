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

import com.app.model.Item;
import com.app.service.IItemService;

@RestController
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	private IItemService service;
	
	@PostMapping("/insert")
	public ResponseEntity<String> saveItem(@RequestBody Item item){
		
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveItems(item);
			resp=new ResponseEntity<String>("item '"+id+"' created", HttpStatus.OK);
			
		}catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllItems(){
		ResponseEntity<?> resp=null;
		try {
			List<Item> list=service.getAllItems();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Item>>(list, HttpStatus.OK);
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
	
	public ResponseEntity<?> getOneItem(@PathVariable("id") Integer id){
		ResponseEntity<?> resp=null;
		try {
		Item i=service.getOneItem(id);
		if(i!=null)
			resp=new ResponseEntity<Item>(i, HttpStatus.OK);
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
	public ResponseEntity<String> deleteOneItem(@PathVariable ("id") Integer id){
		ResponseEntity<String> resp=null;
		try {
			service.deleteItem(id);
			resp=new ResponseEntity<String>("deleted '"+id+"' successfully", HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>("unable to delete", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
	}
   @PutMapping("/edit/{id}")
   public ResponseEntity<String> editData(@PathVariable ("id") Integer id,@RequestBody Item item){
	   ResponseEntity<String> resp=null;
	
		
	if(service.updateItem(id)) {
		   service.getOneItem(id);
	service.saveItems(item);
		   resp=new ResponseEntity<String>("Updated data successfully", HttpStatus.OK);
	
	} else {
	 
	   
		   resp=new ResponseEntity<String>("no id found", HttpStatus.BAD_REQUEST);
		   
		  
	}
	return resp;
   }


}
