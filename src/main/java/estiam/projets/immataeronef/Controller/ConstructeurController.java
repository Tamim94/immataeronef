package estiam.projets.immataeronef.Controller;

import java.util.List;

import estiam.projets.immataeronef.DTO.ConstructeurDTO;
import estiam.projets.immataeronef.Service.ImmatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructeurController {

    @Autowired
    ImmatService immatService;

    @GetMapping("/constructeurs")
    public List<ConstructeurDTO> getAeronefCountByConstructor() {
        return immatService.getAeronefCountByConstructor();
    }
}