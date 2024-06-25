package estiam.projets.immataeronef.Controller;

import java.util.List;

import estiam.projets.immataeronef.DTO.NbAppareilDTO;
import estiam.projets.immataeronef.Service.ImmatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NbAppareilController {

    private final ImmatService immatService;

    public NbAppareilController(ImmatService immatService) {
        this.immatService = immatService;
    }

    @GetMapping("/constructeurs")
    public List<NbAppareilDTO> getNbAppareil() {
        return immatService.getNbAppareil();
    }
}