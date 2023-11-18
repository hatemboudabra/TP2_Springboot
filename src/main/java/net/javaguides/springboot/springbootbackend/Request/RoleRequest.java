package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Roles;

@Data
@Builder
public class RoleRequest {
    private Integer id;
    private String roleName;
    @JsonIgnore
    private UtilisateurRequest utilisateur;
    public static RoleRequest fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }
        return RoleRequest.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public static Roles toEntity(RoleRequest roleRequest) {
        if (roleRequest == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(roleRequest.getId());
        roles.setRoleName(roleRequest.getRoleName());
        roles.setUtilisateur(UtilisateurRequest.toEntity(roleRequest.getUtilisateur()));
        return roles;
    }


}
