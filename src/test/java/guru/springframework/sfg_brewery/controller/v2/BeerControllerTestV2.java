package guru.springframework.sfg_brewery.controller.v2;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

import guru.springframework.sfg_brewery.controller.BeerControllerV2;
import guru.springframework.sfg_brewery.services.v2.BeerServiceV2;
import guru.springframework.sfg_brewery.web.model.v2.BeerDtoV2;
import guru.springframework.sfg_brewery.web.model.v2.BeerStyleEnum;

@WebMvcTest(BeerControllerV2.class)
class BeerControllerTestV2 {
	
	private static final String BASE_URL = "/api/v2/beer";

	@MockBean
	BeerServiceV2 beerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper om;
	
	BeerDtoV2 validBeer;
	
	@BeforeEach
	void setUp() {
		validBeer = BeerDtoV2.builder()
				.beerName("Beer1")
				.beerStyle(BeerStyleEnum.GOSE)
				.upc(123456789012L)
				.build();
	}

	@Test
	void testGet() throws Exception {
		// given
		given(beerService.getBeerById(any())).willReturn(validBeer);
		
		// when/then
		mockMvc.perform(get(BASE_URL + "/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
				.andExpect(jsonPath("$.beerName", is("Beer1")))
				;		
	}
	
	@Test
	void testPost() throws Exception {
		// given
		BeerDtoV2 beerDtoV2 = validBeer;
		beerDtoV2.setId(null);
		BeerDtoV2 savedDto = BeerDtoV2.builder().id(UUID.randomUUID()).beerName("New Beer").build();
		String BeerDtoV2Json = om.writeValueAsString(savedDto);
		
		given(beerService.saveNewBeer(any())).willReturn(savedDto);
		
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(BeerDtoV2Json))
			.andExpect(status().isCreated());
	}
	
	@Test
	void testPut() throws Exception {
		// given		
		String beerJson = om.writeValueAsString(validBeer);
		
		mockMvc.perform(put(BASE_URL + "/" + validBeer.getId().toString())
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerJson))
			.andExpect(status().isNoContent());
	}
	
	@Test
	void testDelete() throws Exception {
		// given
		String testUUIDString = UUID.randomUUID().toString();
		
		// when/then
		mockMvc.perform(delete(BASE_URL + "/" + testUUIDString)).andExpect(status().isNoContent());
	}

}
