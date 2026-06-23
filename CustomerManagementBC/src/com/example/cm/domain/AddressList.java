package com.example.cm.domain;

import java.util.List;

import com.example.ddd.helper.Entity;

@Entity(identity = "id")
public class AddressList extends DomainEntity<AddressListID> {
	private List<Address> addresses;
	
}
