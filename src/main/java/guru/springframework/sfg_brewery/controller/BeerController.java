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

import guru.springframework.sfg_brewery.services.BeerService;
import guru.springframework.sfg_brewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
	
	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		BeerDto dto = beerService.getBeerById(beerId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping // Create new beer
	public ResponseEntity<?> handlePost(@RequestBody BeerDto beerDTO) {
		
		BeerDto savedDto = beerService.saveNewBeer(beerDTO);
		
		HttpHeaders headers = new HttpHeaders();
		// TODO add hostname to URL
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<?> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId, beerDto);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);
	}
	
}
