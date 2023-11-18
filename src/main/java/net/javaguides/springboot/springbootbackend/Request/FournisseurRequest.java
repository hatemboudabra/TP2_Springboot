package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Fournisseur;

import java.util.List;


@Data
@Builder
public class FournisseurRequest {
    private Integer id;
    private String nom;
    private String prenom;
    private AdresseRequest adresse;
    private String photo;
    private String mail;
    private String numTel;
    private Integer idEntreprise;
    @JsonIgnore
    private List<CommandeFournisseurRequest> commandeFournisseurs;

    public static FournisseurRequest fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }
        return FournisseurRequest.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(AdresseRequest.fromEntity(fournisseur.getAdresse()))
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .idEntreprise(fournisseur.getIdEntreprise())
                .build();
    }

    public static Fournisseur toEntity(FournisseurRequest fournisseurRequest) {
        if (fournisseurRequest == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurRequest.getId());
        fournisseur.setNom(fournisseurRequest.getNom());
        fournisseur.setPrenom(fournisseurRequest.getPrenom());
        fournisseur.setAdresse(AdresseRequest.toEntity(fournisseurRequest.getAdresse()));
        fournisseur.setPhoto(fournisseurRequest.getPhoto());
        fournisseur.setMail(fournisseurRequest.getMail());
        fournisseur.setNumTel(fournisseurRequest.getNumTel());
        fournisseur.setIdEntreprise(fournisseurRequest.getIdEntreprise());

        return fournisseur;
    }
}
