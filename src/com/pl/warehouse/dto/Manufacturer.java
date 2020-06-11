package com.pl.warehouse.dto;

public class Manufacturer extends BaseDto implements Comparable<Manufacturer>{

	public Manufacturer(Long id, String name) {
		super(id, name);
	}

	public Manufacturer(String[] manufacturerData) {
		super(Long.valueOf(manufacturerData[0]), manufacturerData[1]);
	}

	public String toCsvData() {
		return getId() + "," +
				getName();
	}

	public static String headers() {
		return "| Numer | Nazwa |";
	}

	@Override
	public String toString() {
		return "|   " + getId() + "   | " + getName();
	}

	@Override
	public int compareTo(Manufacturer o) {
		return this.getId().compareTo(o.getId());
	}
}
