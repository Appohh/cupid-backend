package cupid.main.domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_swipe")
public class Swipe {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "origin_userId")
   private Integer origin_userId;
   @Column(name = "target_userId")
   private Integer target_userId;
   @Column(name = "direction")
   private Integer direction;
   @Column(name = "timestamp")
   private String timestamp;
}
