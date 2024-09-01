package guru.springframework.sfg_brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.sfg_brewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto findCustomerById(UUID customerId) {
		return CustomerDto.builder().name("Joe Buck").build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto customerDto) {		
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto updatedCustomer) {
		log.debug("Updating...");
	}

	@Override
	public void deleteCustomer(UUID customerId) {
		log.debug("Deleting...");
	}
}
