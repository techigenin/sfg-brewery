package guru.springframework.sfg_brewery.services.v2;

import java.util.UUID;

import guru.springframework.sfg_brewery.web.model.v2.BeerDtoV2;

public interface BeerServiceV2 {

	BeerDtoV2 getBeerById(UUID beerId);

	BeerDtoV2 saveNewBeer(BeerDtoV2 BeerDtoV2);

	void updateBeer(UUID beerId, BeerDtoV2 BeerDtoV2);

	void deleteById(UUID beerId);

}
