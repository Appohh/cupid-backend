package cupid.main.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Swipe {
   private Integer id;
   private Integer origin_userId;
   private Integer target_userId;
   private Integer direction;
   private String timestamp;
}
