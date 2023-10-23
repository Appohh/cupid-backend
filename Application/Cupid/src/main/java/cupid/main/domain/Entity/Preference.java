package cupid.main.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Preference {
    private Integer id;
    private Integer gender;
    private Integer location;
    private Integer distance;
    private Integer bodyType;
    private Integer ethnicity;
    private Integer height;
    private Integer weight;
}
