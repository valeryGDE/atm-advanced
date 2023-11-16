package main.java.businesslogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import main.java.enums.Condition;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Filter {

    private List<Map<Condition, String>> conditionStringList;
    private String name;
}