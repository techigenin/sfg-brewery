package guru.springframework.sfg_brewery.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfg_brewery.services.CustomerService;
import guru.springframework.sfg_brewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
		return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> handlePost(@RequestBody CustomerDto customerDto) {
		CustomerDto savedCustomer = customerService.saveNewCustomer(customerDto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/customer" + savedCustomer.getId().toString());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void handleUpdate(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto updatedCustomer) {
		customerService.updateCustomer(customerId, updatedCustomer);		
	}
	

	@DeleteMapping("/{customerId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("customerId") UUID customerId) {
		customerService.deleteCustomer(customerId);		
	}
}
