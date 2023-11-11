package main.java.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataProviders {

    @DataProvider(parallel = true)
    public static Object[][] getFiltersNames() {
        return Arrays.stream(new String[]{"Hello", "Bye", "Node"})
                .map(element -> new Object[]{element})
                .toArray(Object[][]::new);
    }
}