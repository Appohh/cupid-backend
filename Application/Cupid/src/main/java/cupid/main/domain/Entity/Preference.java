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
@Table(name = "tbl_preference")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "distance")
    private Integer distance;
    @Column(name = "bodyType")
    private Integer bodyType;
    @Column(name = "ethnicity")
    private Integer ethnicity;
}
