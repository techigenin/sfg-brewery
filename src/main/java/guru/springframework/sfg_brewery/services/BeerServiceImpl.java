package guru.springframework.sfg_brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.sfg_brewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
	@Override
	public BeerDto getBeerById(UUID beerId) {
		
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Galaxy Cat")
				.beerStyle("Pale Ale")
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDTO) {		
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		// TODO impl - would add a real impl to update beer
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO Auto-generated method stub
		log.debug("Deleting a beer...");
	}

}
