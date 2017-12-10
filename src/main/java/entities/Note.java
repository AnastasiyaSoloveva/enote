package entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="note")
@Data
public class Note {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(name="name", nullable = false, unique = true)
    private String name;

    @NotEmpty
    @Column(name="content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "notebook_id", nullable = false)
    private Notebook notebook;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "note", cascade = {CascadeType.ALL})
    private Set<Tag> tags = new HashSet<>();
}
