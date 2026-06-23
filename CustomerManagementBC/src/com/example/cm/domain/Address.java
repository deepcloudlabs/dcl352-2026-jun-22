package com.example.cm.domain;

import com.example.ddd.helper.ValueObject;

@ValueObject
public record Address(AddressLines line, Country country, City city) {
	public static Address valueOf(String countryValue, String cityValue, String... lines) {
		var addressLines = AddressLines.of(lines);
		var country = new Country(countryValue);
		var city = new City(cityValue);
		return new Address(addressLines, country, city);
	}

}
