package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

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
}
