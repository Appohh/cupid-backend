package cupid.main.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "fName")
    private String fName;

    @NotBlank
    @Column(name = "lName")
    private String lName;

    @NotBlank
    @Column(name = "birthday")
    private String birthday;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private Integer gender;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "preferenceId")
    private Integer preferenceId;

    @Column(name = "locationId")
    private Integer locationId;

    @NotBlank
    @Column(name = "pImage")
    private String pImage;

    @NotBlank
    @Column(name = "bio")
    private String bio;
}