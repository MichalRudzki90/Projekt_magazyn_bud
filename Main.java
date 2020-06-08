package com.pl.warehouse;

import com.pl.warehouse.menu.CategoriesMenu;
import com.pl.warehouse.menu.ItemsMenu;
import com.pl.warehouse.menu.ManufacturersMenu;
import com.pl.warehouse.store.GoodsStore;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		GoodsStore.loadData();
		mainMenu();
		GoodsStore.saveData();
	}

	private static void mainMenu() {
		try (final Scanner choose = new Scanner(System.in)) {
			String choice = null;
			while (!"0".equals(choice)) {
				System.out.println("\n------Menu------");
				System.out.println("Wybierz 0 aby wyjść!\n" +
						"1 - Administracja producentami.\n" +
						"2 - Administracja kategoriami produktów.\n" +
						"3 - Administracja produktami.");
				choice = choose.nextLine();
				switch (choice) {
					case "1":
						ManufacturersMenu.instance.menu(choose);
						choice = null;
						break;
					case "2":
						CategoriesMenu.instance.menu(choose);
						choice = null;
						break;
					case "3":
						ItemsMenu.instance.menu(choose);
						choice = null;
						break;
				}
			}
		}
	}
}
