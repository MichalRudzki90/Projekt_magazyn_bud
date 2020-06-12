package com.pl.warehouse.store;

import com.pl.warehouse.dto.Item;
import com.pl.warehouse.dto.ItemCategory;
import com.pl.warehouse.dto.Manufacturer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public interface GoodsStore {

	String CATEGORIES_FILE_NAME = "resources/categories.csv";

	String ITEMS_FILE_NAME = "resources/items.csv";

	String MANUFACTURERS_FILE_NAME = "resources/manufacturers.csv";

	Map<Long, Item> itemsMap = new TreeMap<>();

	Map<Long, ItemCategory> categoriesMap = new TreeMap<>();

	Map<Long, Manufacturer> manufacturersMap = new TreeMap<>();

	Map<ItemCategory, Set<Item>> itemsByCategory = new TreeMap<>();

	Map<Manufacturer, Set<Item>> itemsByManufacturer = new TreeMap<>();

	static void loadData() {
		loadCategories();
		loadManufacturers();
		loadItems();
		groupItemsByCategory();
		groupItemsByManufacturer();
	}

	static void saveData() {
		saveCategories();
		saveManufacturers();
		saveItems();
	}

	static void groupItemsByCategory() {
		itemsMap.values()
				.forEach(item -> {
					final ItemCategory category = item.getCategory();
					itemsByCategory.computeIfAbsent(category, k -> new TreeSet<>()).add(item);
				});
	}

	static void groupItemsByManufacturer() {
		itemsMap.values()
				.forEach(item -> {
					final Manufacturer manufacturer = item.getManufacturer();
					itemsByManufacturer.computeIfAbsent(manufacturer, k -> new TreeSet<>()).add(item);
				});
	}

	static void regroupCategories() {
		final Map<Long, ItemCategory> deepCopy = new TreeMap<>(categoriesMap);
		categoriesMap.clear();
		final AtomicLong index = new AtomicLong();
		categoriesMap.putAll(deepCopy.values().stream()
				.peek(c -> c.setId(index.getAndIncrement()))
				.collect(Collectors.toMap(ItemCategory::getId, i -> i)));
	}

	static void regroupManufacturers() {
		final Map<Long, Manufacturer> deepCopy = new TreeMap<>(manufacturersMap);
		manufacturersMap.clear();
		final AtomicLong index = new AtomicLong();
		manufacturersMap.putAll(deepCopy.values().stream()
				.peek(m -> m.setId(index.getAndIncrement()))
				.collect(Collectors.toMap(Manufacturer::getId, i -> i)));
	}

	static void regroupItems() {
		final Map<Long, Item> deepCopy = new TreeMap<>(itemsMap);
		itemsMap.clear();
		final AtomicLong index = new AtomicLong();
		itemsMap.putAll(deepCopy.values().stream()
				.peek(i -> i.setId(index.getAndIncrement()))
				.collect(Collectors.toMap(Item::getId, i -> i)));
	}

	private static void loadCategories() {
		try (final Scanner categories = new Scanner(new File(CATEGORIES_FILE_NAME))) {
			while (categories.hasNextLine()) {
				final String[] category = categories.nextLine().split(",");
				final ItemCategory categoryObject = new ItemCategory(category);
				categoriesMap.put(categoryObject.getId(), categoryObject);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadManufacturers() {
		try (final Scanner manufacturers = new Scanner(new File(MANUFACTURERS_FILE_NAME))) {
			while (manufacturers.hasNextLine()) {
				final String[] manufacturer = manufacturers.nextLine().split(",");
				final Manufacturer manufacturerObject = new Manufacturer(manufacturer);
				manufacturersMap.put(manufacturerObject.getId(), manufacturerObject);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadItems() {
		try (final Scanner items = new Scanner(new File(ITEMS_FILE_NAME))) {
			while (items.hasNextLine()) {
				final String[] item = items.nextLine().split(",");
				final Item itemObject = new Item(item, categoriesMap, manufacturersMap);
				itemsMap.put(itemObject.getId(), itemObject);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveCategories() {
		try (final FileWriter writer = new FileWriter(CATEGORIES_FILE_NAME)) {
			final String data = GoodsStore.categoriesMap.values().stream()
					.map(ItemCategory::toCsvData)
					.collect(Collectors.joining("\n"));
			writer.append(data);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveManufacturers() {
		try (final FileWriter writer = new FileWriter(MANUFACTURERS_FILE_NAME)) {
			final String data = GoodsStore.manufacturersMap.values().stream()
					.map(Manufacturer::toCsvData)
					.collect(Collectors.joining("\n"));
			writer.append(data);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveItems() {
		try (final FileWriter writer = new FileWriter(ITEMS_FILE_NAME)) {
			final String data = GoodsStore.itemsMap.values().stream()
					.map(Item::toCsvData)
					.collect(Collectors.joining("\n"));
			writer.append(data);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
