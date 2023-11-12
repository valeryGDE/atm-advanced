package main.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Condition {

    LAUNCH_NAME("Launch name"),
    DESCRIPTION("Description");

    private final String key;
}