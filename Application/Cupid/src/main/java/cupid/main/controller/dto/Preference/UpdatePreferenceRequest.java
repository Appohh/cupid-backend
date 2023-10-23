package cupid.main.controller.dto.Preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdatePreferenceRequest {
    private Integer userId;
    private Integer gender;
    private Integer location;
    private Integer distance;
    private Integer bodyType;
    private Integer ethnicity;
    private Integer height;
    private Integer weight;
}
