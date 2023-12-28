package main.java.ui.businesslogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import main.java.ui.enums.FilterCondition;

import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Filter {

    private Map<FilterCondition, String> conditionStringMap;
    private String name;
}