package cupid.main.domain.adapter;

import cupid.main.domain.Dto.Role.CreateRole;
import cupid.main.domain.Entity.Role;

public interface RoleAdapter {
Role createRole(CreateRole request);
Integer getRole(Integer userId);
}
