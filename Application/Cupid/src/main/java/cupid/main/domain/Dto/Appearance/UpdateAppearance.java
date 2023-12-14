package cupid.main.domain.Dto.Appearance;

import cupid.main.controller.dto.Appearance.UpdateAppearanceRequest;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateAppearance {
    private Integer userId;
    private Integer gender;
    private String location;
    private Integer bodyType;
    private Integer ethnicity;

    public static UpdateAppearance fromRequest(UpdateAppearanceRequest request){
        return UpdateAppearance.builder()
                .userId(request.getUserId())
                .bodyType(request.getBodyType())
                .location(request.getLocation())
                .ethnicity(request.getEthnicity())
                .gender(request.getGender())
                .build();
    }
}
