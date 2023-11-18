package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Model.CommandeFournisseur;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFournisseurRequest {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private EtatCommande etatCommande;
    private Integer idEntreprise;
    private FournisseurRequest fournisseur;
    private List<LigneCommandeFournisseurRequest> ligneCommandeFournisseurs;

    public static CommandeFournisseurRequest fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
        }
        return CommandeFournisseurRequest.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(FournisseurRequest.fromEntity(commandeFournisseur.getFournisseur()))
                .etatCommande(commandeFournisseur.getEtatCommande())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurRequest commandeFournisseurRequest) {
        if (commandeFournisseurRequest == null) {
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurRequest.getId());
        commandeFournisseur.setCode(commandeFournisseurRequest.getCode());
        commandeFournisseur.setDateCommande(commandeFournisseurRequest.getDateCommande());
        commandeFournisseur.setFournisseur(FournisseurRequest.toEntity(commandeFournisseurRequest.getFournisseur()));
        commandeFournisseur.setIdEntreprise(commandeFournisseurRequest.getIdEntreprise());
        commandeFournisseur.setEtatCommande(commandeFournisseurRequest.getEtatCommande());
        return commandeFournisseur;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
