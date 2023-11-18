package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Client;

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
    public static ClientRequest fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        return ClientRequest.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresse(AdresseRequest.fromEntity(client.getAdresse()))
                .photo(client.getPhoto())
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .idEntreprise(client.getIdEntreprise())
                .build();
    }

    public static Client toEntity(ClientRequest clientRequest) {
        if (clientRequest == null) {
            return null;
        }
        Client client = new Client();
        client.setId(clientRequest.getId());
        client.setNom(clientRequest.getNom());
        client.setPrenom(clientRequest.getPrenom());
        client.setAdresse(AdresseRequest.toEntity(clientRequest.getAdresse()));
        client.setPhoto(clientRequest.getPhoto());
        client.setMail(clientRequest.getMail());
        client.setNumTel(clientRequest.getNumTel());
        client.setIdEntreprise(clientRequest.getIdEntreprise());
        return client;
    }

}
