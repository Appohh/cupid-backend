package cupid.main.domain.Dto.Preference;

import cupid.main.controller.dto.Preference.UpdatePreferenceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdatePreference {
    private Integer gender;
    private Integer age;
    private Integer distance;
    private Integer bodyType;
    private Integer ethnicity;
    private Integer height;
    private Integer weight;

    public static UpdatePreference fromRequest(UpdatePreferenceRequest request) {
        return UpdatePreference.builder()
                .bodyType(request.getBodyType())
                .distance(request.getDistance())
                .ethnicity(request.getEthnicity())
                .gender(request.getGender())
                .height(request.getHeight())
                .weight(request.getWeight())
                .age(request.getAge())
                .build();
    }
}
