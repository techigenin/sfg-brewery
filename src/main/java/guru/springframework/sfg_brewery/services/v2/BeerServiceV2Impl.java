package guru.springframework.sfg_brewery.services.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.sfg_brewery.web.model.v2.BeerDtoV2;
import guru.springframework.sfg_brewery.web.model.v2.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceV2Impl implements BeerServiceV2 {
	@Override
	public BeerDtoV2 getBeerById(UUID beerId) {
		
		return BeerDtoV2.builder()
				.id(UUID.randomUUID())
				.beerName("Galaxy Cat")
				.beerStyle(BeerStyleEnum.GOSE)
				.build();
	}

	@Override
	public BeerDtoV2 saveNewBeer(BeerDtoV2 BeerDtoV2) {		
		return BeerDtoV2.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDtoV2 BeerDtoV2) {
		// TODO impl - would add a real impl to update beer
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO Auto-generated method stub
		log.debug("Deleting a beer...");
	}

}
