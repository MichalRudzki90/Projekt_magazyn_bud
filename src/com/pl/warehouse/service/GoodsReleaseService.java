package com.pl.warehouse.service;

import com.pl.warehouse.dto.Item;
import com.pl.warehouse.dto.ItemCategory;
import com.pl.warehouse.dto.Manufacturer;
import com.pl.warehouse.store.GoodsStore;

import static com.pl.warehouse.store.GoodsStore.categoriesMap;
import static com.pl.warehouse.store.GoodsStore.itemsMap;
import static com.pl.warehouse.store.GoodsStore.manufacturersMap;

public class GoodsReleaseService extends GoodsAbstractService {

	public void removeCategory(Long categoryId) {
		final ItemCategory category = categoriesMap.get(categoryId);
		if(category == null) {
			System.out.println("Nie ma kategorii o podanym numerze.");
			return;
		}
		if (!isCategoryAssignedToAnyItem(category)) {
			categoriesMap.remove(categoryId);
			GoodsStore.regroupCategories();
		}
		else {
			System.out.println("Nie można usunąć producenta bo jest przypisany do produktu.");
		}
	}

	public void removeManufacturer(Long manufacturerId) {
		final Manufacturer manufacturer = manufacturersMap.get(manufacturerId);
		if(manufacturer == null) {
			System.out.println("Nie ma producenta o podanym numerze.");
			return;
		}
		if (!isManufacturerAssignedToAnyItem(manufacturer)) {
			manufacturersMap.remove(manufacturerId);
			GoodsStore.regroupManufacturers();
		}
		else {
			System.out.println("Nie można usunąć producenta bo jest przypisany do produktu.");
		}
	}

	public void removeItem(Long itemId) {
		final Item item = itemsMap.get(itemId);
		if (item != null) {
			itemsMap.remove(itemId);
			GoodsStore.regroupItems();
			refreshMaps();
		}
		else {
			System.out.println("Nie ma produktu o podanym numerze");
		}
	}
}
