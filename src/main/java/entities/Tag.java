package entities;

import lombok.Data;

@Data
public class Tag {

    private long id;
    private String name;
    private long noteId;
}
