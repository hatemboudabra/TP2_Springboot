package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
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

}
