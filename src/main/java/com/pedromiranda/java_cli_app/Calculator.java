package com.pedromiranda.java_cli_app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private BigDecimal weightedAvg = BigDecimal.ZERO;
    private int totalShares = 0;
    private BigDecimal lossCarryover = BigDecimal.ZERO;

    public List<TaxResult> calculateTaxes(List<Operation> trades) {
        List<TaxResult> taxResults = new ArrayList<>();

        for (Operation trade : trades) {
            if ("buy".equals(trade.getOperation())) {
                updateWeightedAvg(trade.getUnitCost(), trade.getQuantity());
                taxResults.add(new TaxResult(BigDecimal.ZERO));
            } else {
                BigDecimal tax = calculateTax(trade.getUnitCost(), trade.getQuantity());
                taxResults.add(new TaxResult(tax));
            }
        }
        return taxResults;
    }

    private void updateWeightedAvg(BigDecimal cost, int quantity) {
        BigDecimal totalCost = weightedAvg.multiply(BigDecimal.valueOf(totalShares))
                .add(cost.multiply(BigDecimal.valueOf(quantity)));
        totalShares += quantity;
        weightedAvg = totalCost.divide(BigDecimal.valueOf(totalShares), 2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calculateTax(BigDecimal sellPrice, int quantity) {
        BigDecimal totalSellValue = sellPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalBuyValue = weightedAvg.multiply(BigDecimal.valueOf(quantity));
        BigDecimal profit = totalSellValue.subtract(totalBuyValue);

        if (profit.compareTo(BigDecimal.ZERO) < 0) {
            lossCarryover = lossCarryover.add(profit.abs());
            return BigDecimal.ZERO;
        }

        if (totalSellValue.compareTo(new BigDecimal("20000")) <= 0) {
            return BigDecimal.ZERO;
        }

        if (lossCarryover.compareTo(profit) > 0) {
            lossCarryover = lossCarryover.subtract(profit);
            return BigDecimal.ZERO;
        } else {
            profit = profit.subtract(lossCarryover);
            lossCarryover = BigDecimal.ZERO;
        }

        return profit.multiply(new BigDecimal("0.20")).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
