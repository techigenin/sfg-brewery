package guru.springframework.sfg_brewery.web.model.v2;

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
public class BeerDtoV2 {

	@Default
	private UUID id = UUID.randomUUID();
	
	private String beerName;
	private BeerStyleEnum beerStyle;
	private Long upc;
}
