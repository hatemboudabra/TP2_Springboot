package net.javaguides.springboot.springbootbackend.controller.api;
import static net.javaguides.springboot.springbootbackend.utils.Constants.ENTREPRISE_ENDPOINT;
import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Request.EntrepriseRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("EntrepriseApi")
public interface EntrepriseApi {
    @PostMapping(ENTREPRISE_ENDPOINT + "/create")
    EntrepriseRequest save(@RequestBody EntrepriseRequest entrepriseRequest);
    @GetMapping(ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    EntrepriseRequest findById(@PathVariable("idEntreprise") Integer id);
    @GetMapping(ENTREPRISE_ENDPOINT + "/all")
    List<EntrepriseRequest> findAll();
    @DeleteMapping(ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
