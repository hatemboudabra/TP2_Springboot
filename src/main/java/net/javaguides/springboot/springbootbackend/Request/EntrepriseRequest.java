package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Entreprise;

import java.util.List;


@Data
@Builder
public class EntrepriseRequest {
    private Integer id;
    private String nom;
    private String description;
    private AdresseRequest adresse;
    private String codeFiscal;
    private String photo;
    private String email;
    private String numTel;
    private String steWeb;

    @JsonIgnore
    private List<UtilisateurRequest> utilisateurs;
    public static EntrepriseRequest fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
        }
        return EntrepriseRequest.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresse(AdresseRequest.fromEntity(entreprise.getAdresse()))
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .steWeb(entreprise.getSteWeb())
                .build();
    }

    public static Entreprise toEntity(EntrepriseRequest entrepriseRequest) {
        if (entrepriseRequest == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseRequest.getId());
        entreprise.setNom(entrepriseRequest.getNom());
        entreprise.setDescription(entrepriseRequest.getDescription());
        entreprise.setAdresse(AdresseRequest.toEntity(entrepriseRequest.getAdresse()));
        entreprise.setCodeFiscal(entrepriseRequest.getCodeFiscal());
        entreprise.setPhoto(entrepriseRequest.getPhoto());
        entreprise.setEmail(entrepriseRequest.getEmail());
        entreprise.setNumTel(entrepriseRequest.getNumTel());
        entreprise.setSteWeb(entrepriseRequest.getSteWeb());

        return entreprise;
    }

}
