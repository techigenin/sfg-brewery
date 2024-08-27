package guru.springframework.sfg_brewery.services;

import java.util.UUID;

import guru.springframework.sfg_brewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

}
