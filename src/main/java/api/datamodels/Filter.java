package main.java.api.datamodels;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Filter {
    private String owner;
    private int id;
    private ArrayList<Condition> conditions;
    private String description;
    private String name;
    private ArrayList<Order> orders;
    private String type;
}
