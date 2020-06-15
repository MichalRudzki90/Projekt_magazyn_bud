package com.pl.warehouse.service;

import com.pl.warehouse.dto.Manufacturer;

public interface ManufacturersService {

	boolean isManufacturerExists(String name);

	boolean isManufacturerAssignedToAnyItem(Manufacturer manufacturer);
}
