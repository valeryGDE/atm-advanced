package main.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Condition {

    LAUNCH_NAME("Launch name"),
    OWNER("Owner"),
    DESCRIPTION("Description");

    private final String key;
}