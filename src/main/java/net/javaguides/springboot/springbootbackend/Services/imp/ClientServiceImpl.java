package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Model.CommandeClient;
import net.javaguides.springboot.springbootbackend.Repository.ClientRepository;
import net.javaguides.springboot.springbootbackend.Repository.CommandeClientRepository;
import net.javaguides.springboot.springbootbackend.Request.ClientRequest;
import net.javaguides.springboot.springbootbackend.Services.ClientService;
import net.javaguides.springboot.springbootbackend.exception.EntityNotFoundException;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import net.javaguides.springboot.springbootbackend.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

     private ClientRepository clientRepository;
    private CommandeClientRepository commandeClientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }
    @Override
    public ClientRequest save(ClientRequest clientRequest) {
        List<String> errors = ClientValidator.validate(clientRequest);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", clientRequest);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        return ClientRequest.fromEntity(
                clientRepository.save(
                        ClientRequest.toEntity(clientRequest)
                )
        );    }

    @Override
    public ClientRequest findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null;
        }
        return clientRepository.findById(id)
                .map(ClientRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND)
                );
    }

    @Override
    public List<ClientRequest> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
        if (!commandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
                    ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
        clientRepository.deleteById(id);
    }
}
