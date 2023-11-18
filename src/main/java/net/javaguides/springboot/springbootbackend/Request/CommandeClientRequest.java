package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Model.CommandeClient;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientRequest {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private EtatCommande etatCommande;
    private Integer idEntreprise;
    private ClientRequest client;
    private List<LigneCommandeClientRequest> ligneCommandeClients;
    public static CommandeClientRequest fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }
        return CommandeClientRequest.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(ClientRequest.fromEntity(commandeClient.getClient()))
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();

    }

    public static CommandeClient toEntity(CommandeClientRequest commandeClientRequest) {
        if (commandeClientRequest == null) {
            return null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientRequest.getId());
        commandeClient.setCode(commandeClientRequest.getCode());
        commandeClient.setClient(ClientRequest.toEntity(commandeClientRequest.getClient()));
        commandeClient.setDateCommande(commandeClientRequest.getDateCommande());
        commandeClient.setEtatCommande(commandeClientRequest.getEtatCommande());
        commandeClient.setIdEntreprise(commandeClientRequest.getIdEntreprise());
        return commandeClient;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
