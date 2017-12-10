package entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="notebook")
@Data
public class Notebook {

     @Id
     @Column(name="id", nullable = false)
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @NotEmpty
     @Column(name="name", nullable = false, unique = true)
     private String name;

     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     private User user;

     @OneToMany(mappedBy = "notebook", cascade = {CascadeType.ALL})
     private Set<Note> notes = new HashSet<>();

}
