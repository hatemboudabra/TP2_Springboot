package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Repository.EntrepriseRepository;
import net.javaguides.springboot.springbootbackend.Repository.RolesRepository;
import net.javaguides.springboot.springbootbackend.Request.EntrepriseRequest;
import net.javaguides.springboot.springbootbackend.Request.RolesRequest;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Services.EntrepriseService;
import net.javaguides.springboot.springbootbackend.Services.UtilisateurService;
import net.javaguides.springboot.springbootbackend.exception.EntityNotFoundException;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.validator.EntrepriseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    private EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RolesRepository rolesRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
                                 RolesRepository rolesRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseRequest save(EntrepriseRequest entrepriseRequest) {
        List<String> errors = EntrepriseValidator.validate(entrepriseRequest);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseRequest);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseRequest savedEntreprise = EntrepriseRequest.fromEntity(
                entrepriseRepository.save(EntrepriseRequest.toEntity(entrepriseRequest))
        );

        UtilisateurRequest utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurRequest savedUser = utilisateurService.save(utilisateur);

        RolesRequest rolesRequest = RolesRequest.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(RolesRequest.toEntity(rolesRequest));

        return  savedEntreprise;
    }

    private UtilisateurRequest fromEntreprise(EntrepriseRequest entrepriseRequest) {
        return UtilisateurRequest.builder()
                .adresse(entrepriseRequest.getAdresse())
                .nom(entrepriseRequest.getNom())
                .prenom(entrepriseRequest.getCodeFiscal())
                .email(entrepriseRequest.getEmail())
                .moteDePasse(generateRandomPassword())
                .entreprise(entrepriseRequest)
                .dateDeNaissance(Instant.now())
                .photo(entrepriseRequest.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseRequest findById(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
                );
    }

    @Override
    public List<EntrepriseRequest> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }

}
