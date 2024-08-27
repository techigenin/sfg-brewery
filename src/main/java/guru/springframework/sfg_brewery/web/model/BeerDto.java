package guru.springframework.sfg_brewery.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	@Default
	private UUID id = UUID.randomUUID();
	
	private String beerName;
	private String beerStyle;
	private Long upc;
}
