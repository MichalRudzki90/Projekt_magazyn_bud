package com.pl.warehouse.service;

import com.pl.warehouse.dto.ItemCategory;

public interface CategoriesService {

	boolean isCategoryExists(String name);

	boolean isCategoryAssignedToAnyItem(ItemCategory itemCategory);
}
