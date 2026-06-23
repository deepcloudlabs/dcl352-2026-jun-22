package com.example.cm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cm.application.CustomerManagementApplication;
import com.example.cm.domain.Customer;
import com.example.cm.domain.CustomerID;
import com.example.cm.dto.request.ChangeEmailRequest;
import com.example.cm.dto.request.ChangePrimaryPhoneRequest;
import com.example.cm.dto.request.RegisterCustomerRequest;
import com.example.cm.dto.response.ChangeEmailResponse;
import com.example.cm.dto.response.ChangePrimaryPhoneResponse;
import com.example.cm.dto.response.CustomerResponse;
import com.example.cm.dto.response.RegisterCustomerResponse;


@Service
public class CustomerManagementService {
	private final CustomerManagementApplication customerManagementApplication;
	
	public CustomerManagementService(CustomerManagementApplication customerManagementApplication) {
		this.customerManagementApplication = customerManagementApplication;
	}

	public CustomerResponse findCustomerById(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public RegisterCustomerResponse register(RegisterCustomerRequest registerCustomerRequest) {
		var customerId = CustomerID.of(registerCustomerRequest.customerId());
		var customer = new Customer.Builder()
				                   .customerId(customerId)
				                   .fullName(registerCustomerRequest.firstName(), registerCustomerRequest.lastName())
				                   .build();
		customerManagementApplication.registerCustomer(customer);
		return new RegisterCustomerResponse(registerCustomerRequest.customerId(), "SUCCESS");
	}

	@Transactional
	public ChangeEmailResponse changeEmail(String customerId, ChangeEmailRequest changeEmailRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ChangePrimaryPhoneResponse changePrimaryPhone(String customerId,
			ChangePrimaryPhoneRequest changePrimaryPhoneRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
