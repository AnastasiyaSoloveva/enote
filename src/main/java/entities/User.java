package entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(name="password", nullable = false)
    private String password;

    @NotEmpty
    @Column(name="is_active", nullable = false)
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Notebook> notebooks = new HashSet<>();

}
