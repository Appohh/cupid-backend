package cupid.main.persistence.domain_action;

import cupid.main.domain.Entity.User;
import cupid.main.domain.domain_action.UserDomainAction;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor

public class UserDomainActionImpl implements UserDomainAction {
    private List<User> users;
    @Override
    public User Save(User user) {
        return null;
    }

    @Override
    public User GetById(int id) {
        return null;
    }

    @Override
    public User Delete(int id) {
        return null;
    }

    @Override
    public List<User> GetAll() {
        return null;
    }

    @Override
    public void RetrieveData(){

    }
}
