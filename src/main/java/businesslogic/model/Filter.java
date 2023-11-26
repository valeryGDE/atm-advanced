package main.java.businesslogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import main.java.enums.Condition;

import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Filter {

    private Map<Condition, String> conditionStringMap;
    private String name;
}