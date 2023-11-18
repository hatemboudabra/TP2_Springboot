package net.javaguides.springboot.springbootbackend.Request;


import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Utilisateur;
import net.javaguides.springboot.springbootbackend.Repository.RolesRepository;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<RolesRequest> roles;

    public static UtilisateurRequest fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurRequest.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .moteDePasse(utilisateur.getMoteDePasse())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .adresse(AdresseRequest.fromEntity(utilisateur.getAdresse()))
                .photo(utilisateur.getPhoto())
                .entreprise(EntrepriseRequest.fromEntity(utilisateur.getEntreprise()))
                .roles(
                        utilisateur.getRoles() != null ?
                                utilisateur.getRoles().stream()
                                        .map(RolesRequest::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Utilisateur toEntity(UtilisateurRequest utilisateurRequest) {
        if (utilisateurRequest == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurRequest.getId());
        utilisateur.setNom(utilisateurRequest.getNom());
        utilisateur.setPrenom(utilisateurRequest.getPrenom());
        utilisateur.setEmail(utilisateurRequest.getEmail());
        utilisateur.setMoteDePasse(utilisateurRequest.getMoteDePasse());
        utilisateur.setDateDeNaissance(utilisateurRequest.getDateDeNaissance());
        utilisateur.setAdresse(AdresseRequest.toEntity(utilisateurRequest.getAdresse()));
        utilisateur.setPhoto(utilisateurRequest.getPhoto());
        utilisateur.setEntreprise(EntrepriseRequest.toEntity(utilisateurRequest.getEntreprise()));

        return utilisateur;
    }
}
