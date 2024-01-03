package cupid.main.domain.adapter;

import cupid.main.domain.Dto.User.GetUserResponse;

import java.util.List;

public interface ForYouAdapter {
    List<GetUserResponse> GenerateForYou(Integer userId);
}
