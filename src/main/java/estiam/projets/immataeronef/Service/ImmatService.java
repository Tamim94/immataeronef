package estiam.projets.immataeronef.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import estiam.projets.immataeronef.AppConf;
import estiam.projets.immataeronef.DTO.AeronefDTO;
import estiam.projets.immataeronef.DTO.NbAppareilDTO;
import estiam.projets.immataeronef.ImmatCSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.exceptions.CsvException;

@Service
public class ImmatService {

	private final ImmatCSVReader immatCSVReader;
	public static final String UNKNOWN = "unknown";
	private Map<String, Map<String, String>> entries;

	public ImmatService(@Autowired ImmatCSVReader immatCSVReader, @Autowired AppConf appConf) throws CsvException, IOException {
		this.immatCSVReader = immatCSVReader;
		entries = immatCSVReader.importFile(new File(appConf.getFilename()));
	}

	public Optional<AeronefDTO> getAeronefFromImmat(String immat) {
		var entry = immatCSVReader.getEntryByImmat(immat);
		if (entry.isEmpty()) {
			return Optional.empty();
		}

		var constructeur = entry.getOrDefault("CONSTRUCTEUR", UNKNOWN);
		var modele = entry.getOrDefault("MODELE", UNKNOWN);
		var aerodromeAttache = entry.getOrDefault("AERODROME_ATTACHE", UNKNOWN);

		return Optional.ofNullable(new AeronefDTO(immat, constructeur, modele, aerodromeAttache));
	}

	public List<NbAppareilDTO> getNbAppareil() {
		Map<String, Integer> constructeurCount = new HashMap<>();
		for (Map<String, String> entry : entries.values()) {
			String constructeur = entry.get("CONSTRUCTEUR");
			constructeurCount.put(constructeur, constructeurCount.getOrDefault(constructeur, 0) + 1);
		}

		List<NbAppareilDTO> result = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : constructeurCount.entrySet()) {
			result.add(new NbAppareilDTO(entry.getKey(), entry.getValue()));
		}

		return result;
	}
}
