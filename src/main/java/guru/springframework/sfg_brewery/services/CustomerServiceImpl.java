package guru.springframework.sfg_brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.sfg_brewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {@Override
	public CustomerDto findCustomerById(UUID customerId) {
		return CustomerDto.builder()
				.name("Joe Buck")
				.build();
	}
}
