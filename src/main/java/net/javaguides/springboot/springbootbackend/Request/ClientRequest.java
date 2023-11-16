package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ClientRequest {
    private Integer id;
    private String nom;
    private String prenom;
    private AdresseRequest adresse;
    private String photo;
    private String mail;
    private String numTel;
    private Integer idEntreprise;
    @JsonIgnore
    private List<CommandeClientRequest> commandeClients;

}
