package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.ClientRequest;

import java.util.List;

public interface ClientService {
    ClientRequest save (ClientRequest clientRequest);

    ClientRequest findById(Integer id);

    List<ClientRequest> findAll();

    void delete(Integer id);
}
