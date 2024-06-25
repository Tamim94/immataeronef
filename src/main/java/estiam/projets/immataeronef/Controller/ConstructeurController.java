package estiam.projets.immataeronef.Controller;

import java.util.List;

import estiam.projets.immataeronef.DTO.ConstructeurDTO;
import estiam.projets.immataeronef.Service.ImmatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructeurController {

    private final ImmatService immatService;

    public ConstructeurController(ImmatService immatService) {
        this.immatService = immatService;
    }

    @GetMapping("/constructeurs")
    public List<ConstructeurDTO> getNbAppareil() {
        return immatService.getNbAppareil();
    }
}