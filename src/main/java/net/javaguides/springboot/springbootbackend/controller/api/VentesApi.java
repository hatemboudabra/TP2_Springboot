package net.javaguides.springboot.springbootbackend.controller.api;
import static net.javaguides.springboot.springbootbackend.utils.Constants.VENTES_ENDPOINT;
import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Request.VentesRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("VentesApi")
public interface VentesApi {
    @PostMapping(VENTES_ENDPOINT + "/create")
    VentesRequest save(@RequestBody VentesRequest ventesRequest);

    @GetMapping(VENTES_ENDPOINT + "/{idVente}")
    VentesRequest findById(@PathVariable("idVente") Integer id);

    @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
    VentesRequest findByCode(@PathVariable("codeVente") String code);

    @GetMapping(VENTES_ENDPOINT + "/all")
    List<VentesRequest> findAll();

    @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}
