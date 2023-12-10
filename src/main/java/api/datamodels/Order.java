package main.java.api.datamodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("isAsc")
    private boolean isAsc;
    private String sortingColumn;
}
