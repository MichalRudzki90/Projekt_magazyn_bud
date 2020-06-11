package com.pl.warehouse.dto;

public abstract class BaseDto {

	private Long id;
	private String name;

	public BaseDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BaseDto dto = (BaseDto) o;

		return id.equals(dto.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
