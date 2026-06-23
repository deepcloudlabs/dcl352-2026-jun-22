package com.example.cm.domain;

import java.util.List;
import java.util.Objects;

import com.example.cm.domain.exception.BusinessConstraint;
import com.example.cm.domain.exception.BusinessRule;
import com.example.cm.domain.exception.DomainInvariantViolation;
import com.example.ddd.helper.Entity;

@Entity(identity = "id")
public class CustomerContact extends DomainEntity<ContactID> {
	private Email email;
	private AddressList addresses;
	private PhoneList phones;

	private CustomerContact(Builder builder) {
		super(builder.id);

		this.email = Objects.requireNonNull(builder.email, "email cannot be null");
		this.addresses = builder.addresses;
		this.phones = builder.phones;

		enforceInvariants();
	}

	public static class Builder {
		return new Builder();
	}

	public static Builder builder(ContactID id) {
		return new Builder().id;
	}

	public ContactID contactId() {
		return id();
	}

	public Email email() {
		return email;
	}

	public AddressList addresses() {
		return addresses;
	}

	public PhoneList phones() {
		return phones;
	}

	public boolean hasEmail() {
		return email != null;
	}

	public boolean hasAddresses() {
		return !addresses.isEmpty();
	}

	public boolean hasPhones() {
		return !phones.isEmpty();
	}

	public boolean hasAtLeastOneContactChannel() {
		return email != null || !addresses.isEmpty() || !phones.isEmpty();
	}

	public void changeEmail(Email newEmail) {
		this.email = Objects.requireNonNull(newEmail, "newEmail cannot be null");
		enforceInvariants();
	}

	public void addAddress(Address address) {
		this.addresses.addAddress(address);
		enforceInvariants();
	}

	public void removeAddress(Address address) {
		this.addresses.removeAddress(address);
		enforceInvariants();
	}

	public void addPhone(Phone phone) {
		this.phones.addPhone(phone);
		enforceInvariants();
	}

	public void removePhone(Phone phone) {
		this.phones.removePhone(phone);
		enforceInvariants();
	}

	public void makePrimaryPhone(Phone phone) {
		this.phones.setDefaultPhone(phone);
		enforceInvariants();
	}

	private void enforceInvariants() {

		if (email == null) {
			throw new DomainInvariantViolation(100, "Customer contact must have an email.");
		}

		if (addresses == null) {
			throw new DomainInvariantViolation(200, "Address list cannot be null.");
		}

		if (phones == null) {
			throw new DomainInvariantViolation(150, "Phone list cannot be null.");
		}

		if (!hasAtLeastOneContactChannel()) {
			throw new DomainInvariantViolation(250, "Customer contact must have at least one contact channel.");
		}
	}

	public static final class Builder {

        private ContactID id;
        private Email email;
        private AddressList addresses;
        private PhoneList phones = PhoneList.empty();

        private CustomerContact(Builder builder) {
    		super(builder.contactID);
    		this.email = builder.email;
    		this.addresses = builder.addresses;
    		this.phones = builder.phones;
    	}

    	public Email getEmail() {
    		return this.email;
    	}

    	public AddressList getAddressList() {
    		return this.addresses;
    	}

    	public PhoneList getPhoneList() {
    		return this.phones;
    	}

    	public List<Address> getAddresses() {
    		return this.addresses.getAddresses();
    	}

    	public List<Phone> getPhones() {
    		return this.phones.getPhones();
    	}

    	public Email changeEmail(Email newEmail) {
    		if (Objects.isNull(newEmail))
    			throw new BusinessRule(31, "Customer contact should have an email");

    		this.email = newEmail;
    		return this.email;
    	}

    	public Email changeEmail(String newEmail) {
    		if (Objects.isNull(newEmail) || newEmail.isBlank())
    			throw new BusinessRule(31, "Customer contact should have an email");

    		this.email = new Email(newEmail);
    		return this.email;
    	}

    	public List<Address> addAddress(Address newAddress) {
    		if (Objects.isNull(newAddress))
    			throw new BusinessRule(32, "Address cannot be null");

    		return this.addresses.addAddress(newAddress);
    	}

    	public List<Address> removeAddress(Address address) {
    		if (Objects.isNull(address))
    			throw new BusinessRule(33, "Address cannot be null");

    		return this.addresses.removeAddress(address);
    	}

    	public Address setDefaultAddress(Address defaultAddress) {
    		if (Objects.isNull(defaultAddress))
    			throw new BusinessRule(34, "Default address cannot be null");

    		return this.addresses.setDefaultAddress(defaultAddress);
    	}

    	public Address getDefaultAddress() {
    		return this.addresses.getDefaultAddress();
    	}

    	public List<Phone> addPhone(Phone newPhone) {
    		if (Objects.isNull(newPhone))
    			throw new BusinessRule(35, "Phone cannot be null");

    		return this.phones.addPhone(newPhone);
    	}

    	public List<Phone> removePhone(Phone phone) {
    		if (Objects.isNull(phone))
    			throw new BusinessRule(36, "Phone cannot be null");

    		return this.phones.removePhone(phone);
    	}

    	public Phone setDefaultPhone(Phone defaultPhone) {
    		if (Objects.isNull(defaultPhone))
    			throw new BusinessRule(37, "Default phone cannot be null");

    		return this.phones.setDefaultPhone(defaultPhone);
    	}

    	public Phone getDefaultPhone() {
    		return this.phones.getDefaultPhone();
    	}

    	public boolean hasEmail() {
    		return Objects.nonNull(this.email);
    	}

    	public boolean hasAddresses() {
    		return Objects.nonNull(this.addresses) && !this.addresses.getAddresses().isEmpty();
    	}

    	public boolean hasPhones() {
    		return Objects.nonNull(this.phones) && !this.phones.getPhones().isEmpty();
    	}

    	public boolean hasAtLeastOneContactChannel() {
    		return hasEmail() || hasAddresses() || hasPhones();
    	}

    	public static class Builder {

    		private ContactID contactID;
    		private Email email;
    		private AddressList addresses;
    		private PhoneList phones;

    		public Builder(int id) {
    			this.contactID = new ContactID(id);
    		}

    		public Builder email(String email) {
    			if (Objects.isNull(email) || email.isBlank())
    				throw new BusinessRule(31, "Customer contact should have an email");

    			this.email = new Email(email);
    			return this;
    		}

    		public Builder email(Email email) {
    			this.email = email;
    			return this;
    		}

    		public Builder addresses(AddressList addresses) {
    			this.addresses = addresses;
    			return this;
    		}

    		public Builder phones(PhoneList phones) {
    			this.phones = phones;
    			return this;
    		}

    		public CustomerContact build() {
    			// Consistency Boundary:
    			// 1. Business Rule
    			// 2. Validation Rule
    			// 3. Invariant
    			// 4. Business Constraint
    			// 5. Regulations
    			// 6. Standards

    			if (Objects.isNull(this.contactID))
    				throw new BusinessConstraint(30, "Customer contact id is required");

    			if (Objects.isNull(this.email))
    				throw new BusinessRule(31, "Customer contact should have an email");

    			if (Objects.isNull(this.addresses))
    				throw new BusinessRule(32, "Customer contact should have an address list");

    			if (Objects.isNull(this.phones))
    				throw new BusinessRule(33, "Customer contact should have a phone list");

    			var hasEmail = Objects.nonNull(this.email);
    			var hasAddress = !this.addresses.getAddresses().isEmpty();
    			var hasPhone = !this.phones.getPhones().isEmpty();

    			if (!hasEmail && !hasAddress && !hasPhone)
    				throw new BusinessRule(34, "Customer contact should have at least one contact channel");

    			return new CustomerContact(this);
    		}
    	}
}
