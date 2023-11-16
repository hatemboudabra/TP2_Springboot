package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangerMotDePasseUtilisateurRequest {
    private Integer id;
    private String motDePasse;
    private String confirmMotDePasse;
}
