package com.example.cm.domain;

import java.util.List;

import com.example.ddd.helper.Entity;
// Class, Object, Inheritance, OO Design
// Ubiquitous Language -> Bounded Context
// Customer, CustomerID, FullName, BirthYear, Email, AddressList, PhoneList,... 
// Entity: 1) persistence ii) identity
@Entity(identity="customerId", aggregate = true)
public class Customer extends DomainEntity<CustomerID> {
	private FullName fullName;
	private CustomerVerified verified;
	private CustomerProfile customerProfile;
	private CustomerContact customerContact;

	

}
