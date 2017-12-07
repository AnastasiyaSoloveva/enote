package entities;

import lombok.Data;

@Data
public class User {

    private long id;
    private String email;
    private String password;
    private boolean isActive;

}
