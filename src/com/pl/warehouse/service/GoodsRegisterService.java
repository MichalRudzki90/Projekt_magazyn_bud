package com.pl.warehouse.service;

import com.pl.warehouse.dto.Item;
import com.pl.warehouse.dto.ItemCategory;
import com.pl.warehouse.dto.ItemLocation;
import com.pl.warehouse.dto.Manufacturer;

import static com.pl.warehouse.store.GoodsStore.categoriesMap;
import static com.pl.warehouse.store.GoodsStore.itemsMap;
import static com.pl.warehouse.store.GoodsStore.manufacturersMap;

public class GoodsRegisterService extends GoodsAbstractService {

	public void addCategory(String name) {
		if (!isCategoryExists(name)) {
			final Long nextNumber = (long) categoriesMap.size();
			final ItemCategory category = new ItemCategory(nextNumber, name);
			categoriesMap.put(nextNumber, category);
		}
		else {
			System.out.println("Kategoria o takiej nazwie już istnieje.");
		}
	}

	public void addManufacturer(String name) {
		if (!isManufacturerExists(name)) {
			final Long nextNumber = (long) manufacturersMap.size();
			final Manufacturer manufacturer = new Manufacturer(nextNumber, name);
			manufacturersMap.put(nextNumber, manufacturer);
		}
		else {
			System.out.println("Producent o takiej nazwie już istnieje.");
		}
	}

	public Item addItem(String name, Long categoryId, Long manufacturerId, int quantity) {
		final Long nextNumber = (long) itemsMap.size();
		final ItemCategory itemCategory = categoriesMap.get(categoryId);
		final Manufacturer manufacturer = manufacturersMap.get(manufacturerId);
		final Item item = new Item(nextNumber, name, itemCategory, manufacturer, quantity);
		itemsMap.put(nextNumber, item);
		return item;
	}

	public void addItemDescription(Long itemId, String description) {
		final Item item = itemsMap.get(itemId);
		if (item != null) {
			item.setDescription(description);

		}
		else {
			System.out.println("Brak produktu o podanym numerze");
		}
	}

	public void setItemLocation(Integer storageRack, Integer shelf, Integer place, Item item) {
		item.setLocation(new ItemLocation(storageRack, shelf, place));
	}

	public void changeItemQuantity(Long itemId, int quantity) {
		final Item item = itemsMap.get(itemId);
		if (item != null) {
			item.setQuantity(quantity);
		}
		else {
			System.out.println("Brak produktu o podanym numerze");
		}

	}
}
