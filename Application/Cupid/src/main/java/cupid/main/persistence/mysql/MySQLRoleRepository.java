package cupid.main.persistence.mysql;

import cupid.main.controller.dto.Handler.custom_exceptions.NotFoundException;
import cupid.main.domain.Dto.Role.CreateRole;
import cupid.main.domain.Entity.Role;
import cupid.main.domain.adapter.RoleAdapter;
import cupid.main.persistence.iJpa.IRoleJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MySQLRoleRepository implements RoleAdapter {
    private IRoleJpa jpa;

    public MySQLRoleRepository(IRoleJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Role createRole(CreateRole request) {
        Role role = Role.builder()
                .userId(request.getUserId())
                .role(request.getRole())
                .build();
        return jpa.save(role);
    }

    @Override
    public Integer getRole(Integer userId) {
        Optional<Role> role = jpa.findByUserId(userId);
        if (role.isEmpty()){
            throw new NotFoundException("Role not found");
        }
        return role.get().getRole();
    }
}
