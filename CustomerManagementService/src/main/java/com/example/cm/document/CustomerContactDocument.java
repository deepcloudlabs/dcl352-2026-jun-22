package com.example.cm.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.cm.domain.AddressList;
import com.example.cm.domain.CustomerContact;

public class CustomerContactDocument {
	private int id;
	private String email;
	private int addressListId;
	private List<AddressDocument> addresses;
	private int phoneListId;
	private List<PhoneDocument> phones;

	public CustomerContactDocument() {
	}

	public CustomerContactDocument(int id, String email, int addressListId, 
			List<AddressDocument> addresses, int phoneListId, List<PhoneDocument> phones) {
		this.id = id;
		this.email = email;
		this.addressListId = addressListId;
		this.addresses = addresses;
		this.phoneListId = phoneListId;
		this.phones = phones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getAddressListId() {
		return addressListId;
	}

	public void setAddressListId(int addressListId) {
		this.addressListId = addressListId;
	}

	public int getPhoneListId() {
		return phoneListId;
	}

	public void setPhoneListId(int phoneListId) {
		this.phoneListId = phoneListId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AddressDocument> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDocument> addresses) {
		this.addresses = addresses;
	}

	public List<PhoneDocument> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDocument> phones) {
		this.phones = phones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(id));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerContactDocument other = (CustomerContactDocument) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "CustomerContactDocument [id=" + id + ", email=" + email + ", addresses=" + addresses + ", phones="
				+ phones + "]";
	}

	public static CustomerContactDocument fromCustomer(CustomerContact customerContact) {
		var customerDocumentContact = new CustomerContactDocument();
		customerDocumentContact.setId(customerContact.getIdentity().id());
		customerDocumentContact.setEmail(customerContact.getEmail().value());
		ArrayList<AddressDocument> addresses = customerContact.getAddressList().getAddresses().stream().map(addr -> {
			return new AddressDocument(addr.country().value(), addr.city().value(), addr.defaultAddress(),
					addr.lines().values());
		}).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		customerDocumentContact.setAddresses(addresses);
		ArrayList<PhoneDocument> phones = new ArrayList<PhoneDocument>(
				customerContact.getPhoneList().getPhones().stream().map(phone -> {
					return new PhoneDocument(phone.value(), phone.defaultPhone());
				}).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		customerDocumentContact.setPhones(phones);
		return customerDocumentContact;
	}

	public static CustomerContact toCustomerContact(CustomerContactDocument customerContactDocument) {
		return new CustomerContact.Builder(customerContactDocument.getId())
				                  .email(customerContactDocument.getEmail())
				                  .addresses(new AddressList.Builder(customerContactDocument.getAddressListId())
				                		                        .build())
				                  .build();
	}
}
