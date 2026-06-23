package com.example.cm.domain;

import java.util.List;

import com.example.ddd.helper.Entity;
// Class, Object, Inheritance, OO Design
// Ubiquitous Language -> Bounded Context
// Customer, CustomerID, FullName, BirthYear, Email, AddressList, PhoneList,... 
// Entity: 1) persistence ii) identity
@Entity(identity="customerId", aggregate = true)
public class Customer {
	private CustomerID customerId;
	private FullName fullName;
	private BirthYear birthYear;
	private Email email;
	private AddressList addresses;
	private PhoneList phones;
	private CustomerVerified verified;
}
