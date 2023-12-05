package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.ClientRequest;
import net.javaguides.springboot.springbootbackend.Services.ClientService;
import net.javaguides.springboot.springbootbackend.controller.api.ClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ClientController implements ClientApi {
     private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @Override
    public ClientRequest save(ClientRequest clientRequest) {
        return clientService.save(clientRequest);
    }

    @Override
    public ClientRequest findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientRequest> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}
