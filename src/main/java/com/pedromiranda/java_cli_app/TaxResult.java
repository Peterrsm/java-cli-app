package com.pedromiranda.java_cli_app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TaxResult {
    private BigDecimal tax;
}
