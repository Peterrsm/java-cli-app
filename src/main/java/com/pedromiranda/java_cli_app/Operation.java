package com.pedromiranda.java_cli_app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Operation {
    @JsonProperty("operation")
    private String operation;

    @JsonProperty("unit-cost")
    private BigDecimal unitCost;

    @JsonProperty("quantity")
    private int quantity;
}
