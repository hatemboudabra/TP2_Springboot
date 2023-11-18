package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Repository.UtilisateurRepository;
import net.javaguides.springboot.springbootbackend.Request.ChangerMotDePasseUtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Services.UtilisateurService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.validator.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UtilisateurRequest save(UtilisateurRequest utilisateurRequest) {
        List<String> errors = UtilisateurValidator.validate(utilisateurRequest);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}", utilisateurRequest);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        if(userAlreadyExists(utilisateurRepository.getEmail())) {
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

    @Override
    public UtilisateurRequest findById(Integer id) {
        return null;
    }

    @Override
    public List<UtilisateurRequest> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public UtilisateurRequest findByEmail(String email) {
        return null;
    }

    @Override
    public UtilisateurRequest changerMotDePasse(ChangerMotDePasseUtilisateurRequest changerMotDePasseUtilisateurRequest) {
        return null;
    }
}
