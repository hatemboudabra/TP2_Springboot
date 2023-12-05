package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Model.CommandeClient;
import net.javaguides.springboot.springbootbackend.Model.CommandeFournisseur;
import net.javaguides.springboot.springbootbackend.Repository.CommandeFournisseurRepository;
import net.javaguides.springboot.springbootbackend.Repository.FournisseurRepository;
import net.javaguides.springboot.springbootbackend.Request.FournisseurRequest;
import net.javaguides.springboot.springbootbackend.Services.FournisseurService;
import net.javaguides.springboot.springbootbackend.exception.EntityNotFoundException;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import net.javaguides.springboot.springbootbackend.validator.FournisseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
                                  CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }
    @Override
    public FournisseurRequest save(FournisseurRequest fournisseurRequest) {
        List<String> errors = FournisseurValidator.validate(fournisseurRequest);
        if (!errors.isEmpty()) {
            log.error("Fournisseur is not valid {}", fournisseurRequest);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }

        return FournisseurRequest.fromEntity(
                fournisseurRepository.save(
                        FournisseurRequest.toEntity(fournisseurRequest)
                )
        );
    }

    @Override
    public FournisseurRequest findById(Integer id) {
        if (id == null) {
            log.error("Fournisseur ID is null");
            return null;
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
                );    }

    @Override
    public List<FournisseurRequest> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Fournisseur ID is null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseur.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
                    ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);
    }
}
