package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class AdresseRequest {
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;
}
