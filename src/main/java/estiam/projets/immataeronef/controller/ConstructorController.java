package estiam.projets.immataeronef.controller;

import java.util.List;

import estiam.projets.immataeronef.DTO.ConstructorDTO;
import estiam.projets.immataeronef.service.ImmatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructorController {

    @Autowired
    ImmatService immatService;

    @GetMapping("/constructeurs")
    public List<ConstructorDTO> getAeronefCountByConstructor() {
        return immatService.getAeronefCountByConstructor();
    }
}