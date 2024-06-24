package estiam.projets.immataeronef;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NbAppareilController {

    private final ImmatService immatService;

    public NbAppareilController(ImmatService immatService) {
        this.immatService = immatService;
    }

    @GetMapping("/aeronef/nbAppareil")
    public List<NbAppareilDTO> getNbAppareil() {
        return immatService.getNbAppareil();
    }
}