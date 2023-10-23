package cupid.main.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private Integer id;
    private Integer userId1;
    private Integer userId2;
    private String timestamp;
}
