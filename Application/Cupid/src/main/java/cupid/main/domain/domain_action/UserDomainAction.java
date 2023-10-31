package cupid.main.domain.domain_action;

import cupid.main.domain.Entity.User;

import java.util.List;

public interface UserDomainAction {

    User Save(User user);
    User GetById(int id);
    User Delete(int id);
    List<User> GetAll();


    void RetrieveData();
}
