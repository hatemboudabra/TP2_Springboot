package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.VentesRequest;

import java.util.List;

public interface VentesService {
    VentesRequest save(VentesRequest ventesRequest);

    VentesRequest findById(Integer id);

    VentesRequest findByCode(String code);

    List<VentesRequest> findAll();

    void delete(Integer id);

}
