package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Item;
import com.app.repo.ItemRepository;
import com.app.service.IItemService;
@Service
public class ItemServiceImpl implements IItemService {
	
	@Autowired
	private ItemRepository repo;
	

	@Transactional
	public Integer saveItems(Item it) {
		// TODO Auto-generated method stub
		return repo.save(it).getId();
	}

	@Transactional(readOnly = true)
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	public Item getOneItem(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Transactional
	public void deleteItem(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Transactional
	public boolean updateItem(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

}
