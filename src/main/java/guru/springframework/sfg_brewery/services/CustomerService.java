package guru.springframework.sfg_brewery.services;

import java.util.UUID;

import guru.springframework.sfg_brewery.web.model.CustomerDto;

public interface CustomerService {

	CustomerDto findCustomerById(UUID customerId);

}
