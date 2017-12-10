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
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(name="password", nullable = false)
    private String password;

//    @NotEmpty
//    @Column(name="is_active", nullable = false)
//    private Long isActive;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Notebook> notebooks = new HashSet<>();

    public boolean addNotebook(Notebook notebook) {
        notebook.setUser(this);
        return notebooks.add(notebook);
    }

}
