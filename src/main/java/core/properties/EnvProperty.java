package main.java.core.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnvProperty {

    LOCAL_BASE_URL("local.base.url"),
    REMOTE_BASE_URL("remote.base.url"),
    DEFAULT_LOGIN_NAME("default.login.name"),
    DEFAULT_PASSWORD("default.password"),
    ADMIN_LOGIN_NAME("admin.login.name"),
    ADMIN_PASSWORD("admin.password");

    private final String key;
}