package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	// Inject Customer Service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId) {
		
		Customer theCostomer = customerService.getCustomer(customerId);
		
		// DB return null if id not found
		if(theCostomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return theCostomer;
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		/**
		 * In case an ID is passed to Json => set id to null or empty
		 * ID null/empty ? CustomerDAO applies "INSERT" : "UPDATE"
		 * view .saveOrUpdate() in CustomerDAO 
		 */
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	// Add Mapping for PUT /customers - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		// throw a exception if null
		if(tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		customerService.deleteCustomer(customerId);
		return "Deleted Customer id - " + customerId;
	}
	

}
