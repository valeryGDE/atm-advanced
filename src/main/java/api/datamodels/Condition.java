package main.java.api.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition {
    private String condition;
    private String filteringField;
    private String value;
}
