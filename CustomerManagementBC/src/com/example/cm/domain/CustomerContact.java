package com.example.cm.domain;

import com.example.ddd.helper.Entity;

@Entity(identity = "id")
public class CustomerContact extends DomainEntity<ContactID> {
	private Email email;
	private AddressList addresses;
	private PhoneList phones;
}
