package cupid.main.controller.dto.Appearance;

import cupid.main.controller.dto.Preference.GetPreferenceResponse;
import cupid.main.domain.Entity.Appearance;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAppearanceResponse {
    private Integer id;
    private Integer userId;
    private Integer gender;
    private String location;
    private Integer bodyType;
    private Integer ethnicity;

    public static GetAppearanceResponse fromAppearance(Appearance appearance){
        return GetAppearanceResponse.builder()
                .id(appearance.getId())
                .userId(appearance.getUserId())
                .gender(appearance.getGender())
                .bodyType(appearance.getBodyType())
                .location(appearance.getLocation())
                .ethnicity(appearance.getEthnicity())
                .build();
    }
}
