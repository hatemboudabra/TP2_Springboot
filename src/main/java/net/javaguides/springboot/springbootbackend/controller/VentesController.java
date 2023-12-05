package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.VentesRequest;
import net.javaguides.springboot.springbootbackend.Services.VentesService;
import net.javaguides.springboot.springbootbackend.controller.api.VentesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VentesController implements VentesApi {

     private VentesService ventesService;
    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }
    @Override
    public VentesRequest save(VentesRequest ventesRequest) {
        return ventesService.save(ventesRequest);
    }

    @Override
    public VentesRequest findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesRequest findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesRequest> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
