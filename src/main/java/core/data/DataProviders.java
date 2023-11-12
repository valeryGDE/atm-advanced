package main.java.core.data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(parallel = true)
    public static Object[][] getFiltersNames() {
        return new Object[][]{
//                {"First"},
//                {"Second"},
//                {"Third"},
//                {"Fourth"},
                {"Fifth"}
        };
    }

    @DataProvider(parallel = true)
    public static Object[][] getFiltersList() {
        return new Object[][]{
//                {"NameOne", "NameTwo"},
//                {"HelloOne", "ByeOne"},
//                {"RainOne", "SkyOne"},
//                {"Eat", "Ate"},
                {"Drive", "Drove"}
        };
    }
}