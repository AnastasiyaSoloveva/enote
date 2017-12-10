package entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="tag")
@Data
public class Tag {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(name="name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;
}
