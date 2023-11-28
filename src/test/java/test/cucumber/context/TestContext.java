package test.cucumber.context;

import com.google.inject.Guice;
import com.google.inject.Inject;
import lombok.Getter;
import main.java.businesslogic.areas.FiltersBll;
import main.java.businesslogic.areas.LoginBll;

@Getter
public class TestContext {

    @Inject
    private FiltersBll filtersBll;
    @Inject
    private LoginBll loginBll;

    public TestContext() {
        Guice.createInjector().injectMembers(this);
    }
}
