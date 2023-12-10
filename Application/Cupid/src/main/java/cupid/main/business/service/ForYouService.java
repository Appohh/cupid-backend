package cupid.main.business.service;

import cupid.main.domain.Dto.User.GetUserResponse;
import cupid.main.domain.Entity.User;

import java.util.List;

public interface ForYouService {
    List<GetUserResponse> GenerateForYouList(Integer userId);
}
