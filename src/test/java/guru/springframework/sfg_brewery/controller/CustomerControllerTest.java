package guru.springframework.sfg_brewery.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.sfg_brewery.services.CustomerService;
import guru.springframework.sfg_brewery.web.model.CustomerDto;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
	
	@MockBean
	CustomerService customerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper om;
	
	CustomerDto validCustomer;
	
	@BeforeEach
	void setUp() {
		validCustomer = CustomerDto.builder()
				.name("customer1")
				.build();
	}

	@Test
	void testGet() throws Exception {
		// given
		given(customerService.findCustomerById(any())).willReturn(validCustomer);
		
		// when/then
		mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
				.andExpect(jsonPath("$.name", is("customer1")))
				;		
		
		then(customerService).should().findCustomerById(any(UUID.class));
	}
	
	@Test
	void testPost() throws Exception {
		// given
		CustomerDto customerDto = validCustomer;
		customerDto.setId(null);
		CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID()).name("New Customer").build();
		String customerDtoJson = om.writeValueAsString(savedDto);
		
		given(customerService.saveNewCustomer(any())).willReturn(savedDto);
	
		// when
		mockMvc.perform(post("/api/v1/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(customerDtoJson))
			.andExpect(status().isCreated());
		
		// then
		then(customerService).should().saveNewCustomer(any(CustomerDto.class));
	}
	
	@Test
	void testPut() throws Exception {
		// given		
		String customerJson = om.writeValueAsString(validCustomer);
		
		mockMvc.perform(put("/api/v1/customer/" + validCustomer.getId().toString())
			.contentType(MediaType.APPLICATION_JSON)
			.content(customerJson))
			.andExpect(status().isNoContent());
		
		then(customerService).should().updateCustomer(any(UUID.class), any(CustomerDto.class));
	}
	
	@Test
	void testDelete() throws Exception {
		// given
		String testUUIDString = UUID.randomUUID().toString();
		
		// when/then
		mockMvc.perform(delete("/api/v1/customer/" + testUUIDString)).andExpect(status().isNoContent());
		
		then(customerService).should().deleteCustomer(any(UUID.class));
	}

}
