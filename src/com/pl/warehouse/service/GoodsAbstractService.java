package com.pl.warehouse.service;

import com.pl.warehouse.dto.ItemCategory;
import com.pl.warehouse.dto.Manufacturer;
import com.pl.warehouse.store.GoodsStore;

import static com.pl.warehouse.store.GoodsStore.categoriesMap;
import static com.pl.warehouse.store.GoodsStore.itemsByCategory;
import static com.pl.warehouse.store.GoodsStore.itemsByManufacturer;
import static com.pl.warehouse.store.GoodsStore.itemsMap;
import static com.pl.warehouse.store.GoodsStore.manufacturersMap;

public class GoodsAbstractService implements CategoriesService, ManufacturersService, ItemsService {

	@Override
	public boolean isItemExists(String name) {
		return itemsMap.values().stream()
				.anyMatch(i -> name.equals(i.getName()));
	}

	@Override
	public boolean isManufacturerExists(String name) {
		return manufacturersMap.values().stream()
				.anyMatch(i -> name.equals(i.getName()));
	}

	@Override
	public boolean isCategoryExists(String name) {
		return categoriesMap.values().stream()
				.anyMatch(i -> name.equals(i.getName()));
	}

	@Override
	public boolean isCategoryAssignedToAnyItem(ItemCategory itemCategory) {
		return itemsByCategory.containsKey(itemCategory);
	}

	@Override
	public boolean isManufacturerAssignedToAnyItem(Manufacturer manufacturer) {
		return itemsByManufacturer.containsKey(manufacturer);
	}

	@Override
	public void refreshMaps() {
		itemsByCategory.clear();
		GoodsStore.groupItemsByCategory();
		itemsByManufacturer.clear();
		GoodsStore.groupItemsByManufacturer();
	}
}
