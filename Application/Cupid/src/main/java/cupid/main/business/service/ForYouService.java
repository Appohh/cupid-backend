package cupid.main.business.service;

import cupid.main.domain.Entity.User;

import java.util.List;

public interface ForYouService {
    List<User> GenerateForYouList(Integer userId);
}
