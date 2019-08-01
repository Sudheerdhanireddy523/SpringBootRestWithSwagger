package com.app.service;

import java.util.List;

import com.app.model.Item;

public interface IItemService {
	public Integer saveItems(Item it );
	public List<Item> getAllItems();
	public Item getOneItem(Integer id );
	public void deleteItem(Integer id);
	public boolean updateItem(Integer id);


}
