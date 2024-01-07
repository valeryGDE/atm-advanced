package main.java.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JiraStatuses {
    TO_DO(11),
    IN_PROGRESS(21),
    PASSED(2),
    FAILED(3);

    private final int status;
}