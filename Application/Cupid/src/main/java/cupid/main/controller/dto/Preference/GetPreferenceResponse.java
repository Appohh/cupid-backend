package cupid.main.controller.dto.Preference;

import cupid.main.domain.Entity.Preference;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPreferenceResponse {
    private Integer id;
    private Integer gender;
    private Integer location;
    private Integer distance;
    private Integer bodyType;
    private Integer ethnicity;
    private Integer height;
    private Integer weight;

    public static GetPreferenceResponse fromPreference(Preference preference) {
        return GetPreferenceResponse.builder()
                .id(preference.getId())
                .gender(preference.getGender())
                .bodyType(preference.getBodyType())
                .distance(preference.getDistance())
                .location(preference.getAge())
                .height(preference.getHeight())
                .weight(preference.getWeight())
                .build();
    }
}


