package cupid.main.controller.dto.Preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePreferenceRequest {
    private Integer userId;
    private Integer gender;
    private Integer age;
    private Integer distance;
    private Integer bodyType;
    private Integer ethnicity;

}
