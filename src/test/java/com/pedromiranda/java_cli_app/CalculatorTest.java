package com.pedromiranda.java_cli_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testCalculateTaxes_WhenBuyingShares_ThenNoTaxIsGenerated() {
        Operation buyOperation = new Operation("buy", new BigDecimal("10.00"), 100);
        List<Operation> operations = Arrays.asList(buyOperation);

        List<TaxResult> taxResults = calculator.calculateTaxes(operations);

        assertEquals(1, taxResults.size());
        assertEquals(BigDecimal.ZERO, taxResults.get(0).getTax());
    }

    @Test
    public void testCalculateTaxes_WhenSellingSharesWithNoProfit_ThenNoTaxIsApplied() {
        Operation buyOperation = new Operation("buy", new BigDecimal("10.00"), 100);
        Operation sellOperation = new Operation("sell", new BigDecimal("10.00"), 100);
        List<Operation> operations = Arrays.asList(buyOperation, sellOperation);

        List<TaxResult> taxResults = calculator.calculateTaxes(operations);

        assertEquals(2, taxResults.size());
        assertEquals(BigDecimal.ZERO, taxResults.get(1).getTax());
    }

    @Test
    public void testCalculateTaxes_WhenSellingSharesWithProfit_ThenTaxIsCalculatedOnProfit() {
        Operation buyOperation = new Operation("buy", new BigDecimal("10.00"), 100);
        Operation sellOperation = new Operation("sell", new BigDecimal("20.00"), 100);
        List<Operation> operations = Arrays.asList(buyOperation, sellOperation);

        List<TaxResult> taxResults = calculator.calculateTaxes(operations);

        assertEquals(2, taxResults.size());
        assertEquals(new BigDecimal("200.00").setScale(2), taxResults.get(1).getTax());
    }

    @Test
    public void testCalculateTaxes_WhenSellingSharesWithLossCarryover_ThenLossIsAppliedToReduceTaxableProfit() {
        Operation buyOperation1 = new Operation("buy", new BigDecimal("10.00"), 100);
        Operation sellOperation1 = new Operation("sell", new BigDecimal("5.00"), 100);
        Operation buyOperation2 = new Operation("buy", new BigDecimal("20.00"), 100);
        Operation sellOperation2 = new Operation("sell", new BigDecimal("30.00"), 100);
        List<Operation> operations = Arrays.asList(buyOperation1, sellOperation1, buyOperation2, sellOperation2);

        List<TaxResult> taxResults = calculator.calculateTaxes(operations);

        assertEquals(4, taxResults.size());
        assertEquals(BigDecimal.ZERO, taxResults.get(1).getTax());
        assertEquals(new BigDecimal("200.00").setScale(2), taxResults.get(3).getTax());
    }
}