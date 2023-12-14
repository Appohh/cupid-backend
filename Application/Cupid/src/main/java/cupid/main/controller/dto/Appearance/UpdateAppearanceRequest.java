package cupid.main.controller.dto.Appearance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppearanceRequest {
    private Integer userId;
    private Integer gender;
    private String location;
    private Integer bodyType;
    private Integer ethnicity;
}
