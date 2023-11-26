package main.java.core.data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(parallel = true)
    public static Object[][] getFiltersNamesForCreation() {
        return new Object[][]{
                {"First"},
                {"Second"},
                {"Third"},
                {"Fourth"},
                {"Fifth"}
        };
    }

    @DataProvider(parallel = true)
    public static Object[][] getFiltersNamesForSaving() {
        return new Object[][]{
                {"Cat"},
                {"Dog"},
                {"Deer"},
                {"Pig"},
                {"Cow"}
        };
    }

    @DataProvider(parallel = true)
    public static Object[][] getFiltersList() {
        return new Object[][]{
                {"Name"},
                {"Hello"},
                {"Rain"},
                {"Eat"},
                {"Drive"}
        };
    }
}