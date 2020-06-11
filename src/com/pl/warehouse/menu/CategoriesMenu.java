package com.pl.warehouse.menu;

import com.pl.warehouse.service.GoodsRegisterService;
import com.pl.warehouse.service.GoodsReleaseService;
import com.pl.warehouse.service.GoodsVerificationService;
import java.util.Scanner;

public class CategoriesMenu {

	final public static CategoriesMenu instance = new CategoriesMenu(
			new GoodsRegisterService(),
			new GoodsVerificationService(),
			new GoodsReleaseService()
	);

	private final GoodsRegisterService goodsRegisterService;
	private final GoodsVerificationService goodsVerificationService;
	private final GoodsReleaseService goodsReleaseService;

	private CategoriesMenu(
			GoodsRegisterService goodsRegisterService,
			GoodsVerificationService goodsVerificationService,
			GoodsReleaseService goodsReleaseService
	) {
		this.goodsRegisterService = goodsRegisterService;
		this.goodsVerificationService = goodsVerificationService;
		this.goodsReleaseService = goodsReleaseService;
	}

	public void menu(Scanner scanner) {
		String choice = null;
		while (!"0".equals(choice)) {
			System.out.println("\n---Menu kategorii produktów---");
			System.out.println("Wybierz 0 aby wyjść do głównego menu!\n" +
					"1 - Dodawanie kategorii produktów.\n" +
					"2 - Lista kategorii.\n" +
					"3 - Lista produktów według kategorii.\n" +
					"4 - Usuwanie kategorii.");
			choice = scanner.nextLine();
			switch (choice) {
				case "1":
					addCategory(scanner);
					choice = null;
					break;
				case "2":
					goodsVerificationService.listCategories();
					choice = null;
					break;
				case "3":
					listByCategory(scanner);
					choice = null;
					break;
				case "4":
					removeCategory(scanner);
					choice = null;
					break;
			}
		}
	}

	private void addCategory(Scanner scanner) {
		System.out.println("Podaj nazwe kategorii");
		String name = scanner.nextLine();
		goodsRegisterService.addCategory(name);
	}

	private void listByCategory(Scanner scanner) {
		System.out.println("Podaj id kategorii");
		Long id = Long.valueOf(scanner.nextLine());
		goodsVerificationService.listItemsByCategory(id);
	}

	private void removeCategory(Scanner scanner) {
		System.out.println("Podaj id kategorii");
		Long id = Long.valueOf(scanner.nextLine());
		goodsReleaseService.removeCategory(id);
	}

}
