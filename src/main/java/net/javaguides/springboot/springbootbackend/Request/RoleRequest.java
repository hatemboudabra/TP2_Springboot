package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class RoleRequest {
    private Integer id;
    private String roleName;

    private UtilisateurRequest utilisateur;

}
