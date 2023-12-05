package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Model.Utilisateur;
import net.javaguides.springboot.springbootbackend.Repository.UtilisateurRepository;
import net.javaguides.springboot.springbootbackend.Request.ChangerMotDePasseUtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Services.UtilisateurService;
import net.javaguides.springboot.springbootbackend.exception.EntityNotFoundException;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import net.javaguides.springboot.springbootbackend.validator.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurRequest save(UtilisateurRequest utilisateurRequest) {
        List<String> errors = UtilisateurValidator.validate(utilisateurRequest);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}", utilisateurRequest);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        if(userAlreadyExists(utilisateurRequest.getEmail()))  {
            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
        }


        utilisateurRequest.setMoteDePasse(passwordEncoder.encode(utilisateurRequest.getMoteDePasse()));

        return UtilisateurRequest.fromEntity(
                utilisateurRepository.save(
                        UtilisateurRequest.toEntity(utilisateurRequest)
                )
        );
    }

    private boolean userAlreadyExists(String  email) {
        Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
        return user.isPresent();
    }


    @Override
    public UtilisateurRequest findById(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }

    @Override
    public List<UtilisateurRequest> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurRequest findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );

    }

    @Override
    public UtilisateurRequest changerMotDePasse(ChangerMotDePasseUtilisateurRequest changerMotDePasseUtilisateurRequest) {
        validate(changerMotDePasseUtilisateurRequest);
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(changerMotDePasseUtilisateurRequest.getId());
        if (utilisateurOptional.isEmpty())  {
            log.warn("Aucun utilisateur n'a ete trouve avec l'ID " + changerMotDePasseUtilisateurRequest.getId());
            throw new EntityNotFoundException("Aucun utilisateur n'a ete trouve avec l'ID " + changerMotDePasseUtilisateurRequest.getId(), ErrorCodes.UTILISATEUR_NOT_FOUND);
        }

        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setMoteDePasse(passwordEncoder.encode(changerMotDePasseUtilisateurRequest.getMotDePasse()));

        return UtilisateurRequest.fromEntity(
                utilisateurRepository.save(utilisateur)
        );
    }
    private void validate(ChangerMotDePasseUtilisateurRequest changerMotDePasseUtilisateurRequest) {
        if (changerMotDePasseUtilisateurRequest == null) {
            log.warn("Impossible de modifier le mot de passe avec un objet NULL");
            throw new InvalidOperationException("Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (changerMotDePasseUtilisateurRequest.getId() == null) {
            log.warn("Impossible de modifier le mot de passe avec un ID NULL");
            throw new InvalidOperationException("ID utilisateur null:: Impossible de modifier le mote de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!StringUtils.hasLength(changerMotDePasseUtilisateurRequest.getMotDePasse()) || !StringUtils.hasLength(changerMotDePasseUtilisateurRequest.getConfirmMotDePasse())) {
            log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
            throw new InvalidOperationException("Mot de passe utilisateur null:: Impossible de modifier le mote de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!changerMotDePasseUtilisateurRequest.getMotDePasse().equals(changerMotDePasseUtilisateurRequest.getConfirmMotDePasse())) {
            log.warn("Impossible de modifier le mot de passe avec deux mots de passe different");
            throw new InvalidOperationException("Mots de passe utilisateur non conformes:: Impossible de modifier le mote de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }



}
