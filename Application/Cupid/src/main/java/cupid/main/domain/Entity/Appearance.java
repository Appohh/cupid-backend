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
@Table(name = "tbl_appearance")
public class Appearance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "location")
    private String location;
    @Column(name = "bodyType")
    private Integer bodyType;
    @Column(name = "ethnicity")
    private Integer ethnicity;
}
