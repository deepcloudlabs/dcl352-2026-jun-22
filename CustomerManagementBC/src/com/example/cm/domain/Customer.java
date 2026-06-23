package com.example.cm.domain;

import java.util.List;

import com.example.ddd.helper.Entity;
// Ubiquitous Language -> Bounded Context
// Customer, CustomerID, FullName, BirthYear, Email, AddressList, PhoneList,... 
// Entity: 1) persistence ii) identity
@Entity(identity="customerId")
public class Customer {
	private CustomerID customerId;
	private FullName fullName;
	private BirthYear birthYear;
	private Email email;
	private AddressList addresses;
	private PhoneList phones;
}
