package com.pl.warehouse.menu;

import com.pl.warehouse.service.GoodsRegisterService;
import com.pl.warehouse.service.GoodsReleaseService;
import com.pl.warehouse.service.GoodsVerificationService;
import java.util.Scanner;

public class ManufacturersMenu {

	final public static ManufacturersMenu instance = new ManufacturersMenu(
			new GoodsRegisterService(),
			new GoodsVerificationService(),
			new GoodsReleaseService()
	);

	private final GoodsRegisterService goodsRegisterService;
	private final GoodsVerificationService goodsVerificationService;
	private final GoodsReleaseService goodsReleaseService;

	private ManufacturersMenu(
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
			System.out.println("\n------Menu producentów------");
			System.out.println("Wybierz 0 aby wyjść do głównego menu!\n" +
					"1 - Dodawanie producenta.\n" +
					"2 - Lista producentów.\n" +
					"3 - Lista produktów producenta.\n" +
					"4 - Usuwanie producenta.");
			choice = scanner.nextLine();
			switch (choice) {
				case "1":
					addManufacturer(scanner);
					choice = null;
					break;
				case "2":
					goodsVerificationService.listManufacturers();
					choice = null;
					break;
				case "3":
					listByManufacturer(scanner);
					choice = null;
					break;
				case "4":
					removeManufacturer(scanner);
					choice = null;
					break;
			}
		}
	}

	private void addManufacturer(Scanner scanner) {
		System.out.println("Podaj nazwe producenta");
		String name = scanner.nextLine();
		goodsRegisterService.addManufacturer(name);
	}

	private void listByManufacturer(Scanner scanner) {
		System.out.println("Podaj id producenta");
		Long id = Long.valueOf(scanner.nextLine());
		goodsVerificationService.listItemsByManufacturer(id);
	}

	private void removeManufacturer(Scanner scanner) {
		System.out.println("Podaj id producenta");
		Long id = Long.valueOf(scanner.nextLine());
		goodsReleaseService.removeManufacturer(id);
	}
}
// klasa bedzie odnosic sie do administracji producentami