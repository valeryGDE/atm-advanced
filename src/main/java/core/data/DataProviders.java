package main.java.core.data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(parallel = true)
    public static Object[][] getFiltersNames() {
        return new Object[][]{
                {"First"},
                {"Second"},
                {"Third"},
                {"Fourth"},
                {"Fifth"}
        };
    }

    @DataProvider(parallel = true)
    public Object[][] getFiltersList() {
        return new Object[][]{
                {"FilterNameOne", "DescriptionOne"},
                {"HelloNameOne", "ByeOne"},
                {"RainOne", "SkyOne"},
                {"Eat", "Ate"},
                {"Drive", "Drove"},
        };
    }
}