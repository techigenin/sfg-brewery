package guru.springframework.sfg_brewery.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfg_brewery.services.CustomerService;
import guru.springframework.sfg_brewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private CustomerService customerService;

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId) {
		return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
	}
}
