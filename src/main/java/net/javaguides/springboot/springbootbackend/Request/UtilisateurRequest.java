package net.javaguides.springboot.springbootbackend.Request;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class UtilisateurRequest {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Instant dateDeNaissance;
    private String moteDePasse;
    private AdresseRequest adresse;
    private String photo;
    private EntrepriseRequest entreprise;
    private List<RoleRequest> roles;
}
