package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Adresse;


@Builder
@Data
public class AdresseRequest {
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;

    public static AdresseRequest fromEntity(Adresse adresse) {
        if (adresse == null) {
            return null;
        }

        return AdresseRequest.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostale(adresse.getCodePostale())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();
    }

    public static Adresse toEntity(AdresseRequest adresseRequest) {
        if (adresseRequest == null) {
            return null;
        }
        Adresse adresse = new Adresse();
        adresse.setAdresse1(adresseRequest.getAdresse1());
        adresse.setAdresse2(adresseRequest.getAdresse2());
        adresse.setCodePostale(adresseRequest.getCodePostale());
        adresse.setVille(adresseRequest.getVille());
        adresse.setPays(adresseRequest.getPays());
        return adresse;
    }
}
