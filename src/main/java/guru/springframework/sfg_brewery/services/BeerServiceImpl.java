package guru.springframework.sfg_brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.sfg_brewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

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

}
