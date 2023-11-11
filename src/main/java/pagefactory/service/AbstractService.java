package main.java.pagefactory.service;

import org.openqa.selenium.WebDriver;

public abstract class AbstractService {

    protected WebDriver driver;

    protected AbstractService(WebDriver driver) {
        this.driver = driver;
    }
}