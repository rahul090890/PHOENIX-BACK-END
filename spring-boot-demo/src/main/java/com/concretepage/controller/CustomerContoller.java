package com.concretepage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.entity.Customer;
import com.concretepage.service.ICustomerService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/customer")
public class CustomerContoller {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICustomerService service;

	@GetMapping("all")
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = service.getCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping("find/{customerName}")
	public ResponseEntity<List<Customer>> findCustomerByName(@PathVariable("customerName") String customerName) {
		List<Customer> customers = service.findByName(customerName);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/create/{customerName}/{address}/{country}/{zipCode}")
	public ResponseEntity<Void> create(
			@PathVariable("customerName") String customerName,
			@PathVariable("address") String address,
			@PathVariable("country") String country,
			@PathVariable("zipCode") String zipCode
			) {
		Customer customer = new Customer();
		customer.setCustomerName(customerName);
		customer.setAddress(address);
		customer.setCountry(country);
		customer.setZipCode(zipCode);
		log.info("Creating the customer " + customer.toString() );
		service.create(customer);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/update/{customerId}/{customerName}/{address}/{country}/{zipCode}")
	public ResponseEntity<Void> update(
			@PathVariable("customerId") String customerId,
			@PathVariable("customerName") String customerName,
			@PathVariable("address") String address,
			@PathVariable("country") String country,
			@PathVariable("zipCode") String zipCode
			) {
		Customer customer = new Customer();
		customer.setCustomerId(Integer.parseInt(customerId));
		customer.setCustomerName(customerName);
		customer.setAddress(address);
		customer.setCountry(country);
		customer.setZipCode(zipCode);
		log.info("Updating the customer " + customer.toString() );
		service.update(customer);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping("delete/{customerId}")
	public ResponseEntity<Void> delete (@PathVariable("customerId") String customerId) {
		
		Customer customer = new Customer();
		customer.setCustomerId(Integer.parseInt(customerId));
		log.info("Deleting the customer " + customer.toString() );
		service.delete(customer);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
