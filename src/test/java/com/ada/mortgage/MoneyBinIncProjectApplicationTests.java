package com.ada.mortgage;

import com.ada.mortgage.model.Prospect;
import com.ada.mortgage.service.MortgageService;
import com.ada.mortgage.util.MortgageUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoneyBinIncProjectApplicationTests {

    @Autowired
    private MortgageService mortgageService;

    @Test
    void testPower() {
        Assertions.assertEquals(100.0, MortgageUtil.power(10, 2));
    }

    @Test
    void testCalculateMonthlyPayment() {
        Prospect prospect = new Prospect();
        prospect.setTotalLoan(300000);
        prospect.setYearlyInterestRate(5);
        prospect.setNumberOfYears(30);
        double monthlyPayment = mortgageService.calculateMonthlyPayment(prospect);
        Assertions.assertEquals(Double.toString(1610.46), String.format("%.2f", monthlyPayment));
    }

}
