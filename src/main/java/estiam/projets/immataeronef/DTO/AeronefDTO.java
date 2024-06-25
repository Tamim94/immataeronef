package estiam.projets.immataeronef.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AeronefDTO {

	private String immatriculation; 
	private String constructeur;
	private String modele;
	private String aerodromeAttache;
	
}
