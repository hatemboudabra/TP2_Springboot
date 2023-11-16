package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesRequest {
    private Integer id;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private Integer idEntreprise;
    private List<LigneVenteRequest> ligneVentes;
}
