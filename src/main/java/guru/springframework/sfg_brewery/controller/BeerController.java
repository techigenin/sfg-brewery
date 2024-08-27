package guru.springframework.sfg_brewery.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfg_brewery.services.BeerService;
import guru.springframework.sfg_brewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
	
	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
		BeerDto dto = beerService.getBeerById(beerId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
