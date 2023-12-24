package main.java.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilterCondition {

    LAUNCH_NAME("Launch name"),
    DESCRIPTION("Description");

    private final String key;
}