package com.pl.warehouse.dto;

public class ItemCategory extends BaseDto implements Comparable<ItemCategory> {

	public ItemCategory(Long id, String name) {
		super(id, name);
	}

	public ItemCategory(String[] categoryData) {
		super(Long.valueOf(categoryData[0]), categoryData[1]);
	}

	public static String headers() {
		return "| Numer | Nazwa |";
	}

	public String toCsvData() {
		return getId() + "," +
				getName();
	}

	@Override
	public String toString() {
		return "|   " + getId() + "   | " + getName();
	}

	@Override
	public int compareTo(ItemCategory o) {
		return this.getId().compareTo(o.getId());
	}
}

// Test