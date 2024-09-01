package guru.springframework.sfg_brewery.controller;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.sfg_brewery.services.BeerService;
import guru.springframework.sfg_brewery.web.model.BeerDto;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
	
	@MockBean
	BeerService beerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper om;
	
	BeerDto validBeer;
	
	@BeforeEach
	void setUp() {
		validBeer = BeerDto.builder()
				.beerName("Beer1")
				.beerStyle("PALE_ALE")
				.upc(123456789012L)
				.build();
	}

	@Test
	void testGet() throws Exception {
		// given
		given(beerService.getBeerById(any())).willReturn(validBeer);
		
		// when/then
		mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
				.andExpect(jsonPath("$.beerName", is("Beer1")))
				;		
	}
	
	@Test
	void testPost() throws Exception {
		// given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
		String beerDtoJson = om.writeValueAsString(savedDto);
		
		given(beerService.saveNewBeer(any())).willReturn(savedDto);
		
		mockMvc.perform(post("/api/v1/beer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerDtoJson))
			.andExpect(status().isCreated());
	}
	
	@Test
	void testPut() throws Exception {
		// given		
		String beerJson = om.writeValueAsString(validBeer);
		
		mockMvc.perform(put("/api/v1/beer/" + validBeer.getId().toString())
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerJson))
			.andExpect(status().isNoContent());
	}
	
	@Test
	void testDelete() throws Exception {
		// given
		String testUUIDString = UUID.randomUUID().toString();
		
		// when/then
		mockMvc.perform(delete("/api/v1/beer/" + testUUIDString)).andExpect(status().isNoContent());
	}

}
