package estiam.projets.immataeronef.service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import estiam.projets.immataeronef.config.AppConf;
import estiam.projets.immataeronef.DTO.AeronefDTO;
import estiam.projets.immataeronef.DTO.ConstructorDTO;
import estiam.projets.immataeronef.data.ImmatCSVReader;
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

	public List<ConstructorDTO> getAeronefCountByConstructor() {
		Map<String, Integer> aeronefCountByConstructor = new HashMap<>();
		for (Map<String, String> entry : entries.values()) {
			String constructor = entry.get("CONSTRUCTEUR");
			if (!constructor.isEmpty()) {
				String upperConstructor = constructor.toUpperCase();
				aeronefCountByConstructor.put(upperConstructor, aeronefCountByConstructor.getOrDefault(upperConstructor, 0) + 1);
			}
		}

		List<ConstructorDTO> result = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : aeronefCountByConstructor.entrySet()) {
			result.add(new ConstructorDTO(entry.getKey(), entry.getValue()));
		}

		return result;
	}
}
