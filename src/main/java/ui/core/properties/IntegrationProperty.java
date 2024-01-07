package main.java.ui.core.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IntegrationProperty {

    EMAIL_URL("email.url"),
    JIRA_URL("jira.url");

    private final String key;
}
