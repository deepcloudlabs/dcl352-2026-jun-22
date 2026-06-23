package com.example.hexagonal;

public enum PortType {
	DRIVING_PORT("API"), DRIVEN_PORT("SPI");

	private final String name;
	
	private PortType(String name) {
		this.name = name;
	}

	public String getPortName() {
		return name;
	}
	
}
