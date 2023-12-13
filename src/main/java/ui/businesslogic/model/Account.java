package main.java.ui.businesslogic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Account {

    private String email;
    private String password;
}