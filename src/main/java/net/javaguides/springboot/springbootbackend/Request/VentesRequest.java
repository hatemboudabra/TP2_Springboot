package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Ventes;

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
    public static VentesRequest fromEntity(Ventes vente) {
        if (vente == null) {
            return null;
        }
        return VentesRequest.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .commentaire(vente.getCommentaire())
                .idEntreprise(vente.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VentesRequest ventesRequest) {
        if (ventesRequest == null) {
            return null;
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesRequest.getId());
        ventes.setCode(ventes.getCode());
        ventes.setCommentaire(ventesRequest.getCommentaire());
        ventes.setIdEntreprise(ventesRequest.getIdEntreprise());
        return ventes;
    }


}
