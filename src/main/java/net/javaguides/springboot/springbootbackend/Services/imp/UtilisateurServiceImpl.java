package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Services.UtilisateurService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    @Override
    public UtilisateurRequest findByEmail(String email) {
        return null;
    }
}
