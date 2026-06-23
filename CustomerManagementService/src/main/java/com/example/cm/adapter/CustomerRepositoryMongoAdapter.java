package com.example.cm.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cm.document.CustomerContactDocument;
import com.example.cm.document.CustomerDocument;
import com.example.cm.domain.Customer;
import com.example.cm.domain.CustomerID;
import com.example.cm.repository.CustomerDocumentRepository;
import com.example.cm.repository.CustomerRepository;
import com.example.hexagonal.helper.Adapter;

@Adapter(port = CustomerRepository.class)
@Repository
public class CustomerRepositoryMongoAdapter implements CustomerRepository {
	private final CustomerDocumentRepository customerDocumentRepository;

	public CustomerRepositoryMongoAdapter(CustomerDocumentRepository customerDocumentRepository) {
		this.customerDocumentRepository = customerDocumentRepository;
	}

	@Override
	@Transactional
	public Customer persist(Customer customer) {
		CustomerDocument  customerDocument = mapCustomerToDocument(customer);
		customerDocument = customerDocumentRepository.save(customerDocument );
		return mapDocumentToCustomer(customerDocument);
	}

	@Override
	public Optional<Customer> findCustomerById(CustomerID customerId) {
		return customerDocumentRepository.findById(customerId.getValue()).map(CustomerDocument::toCustomer);
	}

	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) {
		CustomerDocument  customerDocument = mapCustomerToDocument(customer);		
		var updatedCustomerDocument = customerDocumentRepository.save(customerDocument);
		return mapDocumentToCustomer(updatedCustomerDocument);
	}
	
	private CustomerDocument mapCustomerToDocument(Customer customer) {
		var customerDocument = new CustomerDocument();
		customerDocument.setCustomerId(customer.getIdentity().getValue());
		var customerContact = customer.getCustomerContact();
		customerDocument.setCustomerContact(CustomerContactDocument.fromCustomer(customerContact));
		customerDocument.setFirstName(customer.getFullName().firstName());
		customerDocument.setLastName(customer.getFullName().lastName());
		customerDocument.setVerified(customer.getVerified());
		return customerDocument;
	}
	
	private Customer mapDocumentToCustomer(CustomerDocument customerDocument) {
		return new Customer.Builder()
				            .customerId(customerDocument.getCustomerId())
				            .fullName(customerDocument.getFirstName(), customerDocument.getLastName())
				            .build();
	}
}
