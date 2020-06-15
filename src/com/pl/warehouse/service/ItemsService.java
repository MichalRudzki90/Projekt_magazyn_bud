package com.pl.warehouse.service;


public interface ItemsService {

	boolean isItemExists(String name);

	void refreshMaps();
}
