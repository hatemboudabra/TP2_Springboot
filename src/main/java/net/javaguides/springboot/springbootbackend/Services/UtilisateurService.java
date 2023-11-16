package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;

public interface UtilisateurService {
    UtilisateurRequest findByEmail(String email);

}
