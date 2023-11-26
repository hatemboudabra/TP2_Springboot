package net.javaguides.springboot.springbootbackend.controller.api;
import static net.javaguides.springboot.springbootbackend.utils.Constants.FOURNISSEUR_ENDPOINT;
import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Request.FournisseurRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("FournisseurApi")
public interface FournisseurApi {
    @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
    FournisseurRequest save(@RequestBody FournisseurRequest fournisseurRequest);
    @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
    FournisseurRequest findById(@PathVariable("idFournisseur") Integer id);
    @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
    List<FournisseurRequest> findAll();
    @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Integer id);
}
