package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Roles;

@Data
@Builder
public class RolesRequest {
    private Integer id;
    private String roleName;
    @JsonIgnore
    private UtilisateurRequest utilisateur;
    public static RolesRequest fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }
        return RolesRequest.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public static Roles toEntity(RolesRequest roleRequest) {
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
