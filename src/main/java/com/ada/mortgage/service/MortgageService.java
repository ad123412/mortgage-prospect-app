package com.ada.mortgage.service;

import com.ada.mortgage.model.Prospect;
import org.springframework.stereotype.Service;

import static com.ada.mortgage.util.MortgageUtil.power;

@Service
public class MortgageService {
    public double calculateMonthlyPayment(
            double totalLoanAmount,
            double monthlyInterest,
            int numberOfInstallments) {
        return totalLoanAmount * (monthlyInterest * power(1 + monthlyInterest, numberOfInstallments)) /
                (power(1 + monthlyInterest, numberOfInstallments) - 1);
    }

    public double calculateMonthlyPayment(Prospect prospect) {
        double monthlyInterest = prospect.getYearlyInterestRate() / (100 * 12);
        int numberOfInstallments = prospect.getNumberOfYears() * 12;
        double monthlyPayment =
                calculateMonthlyPayment(prospect.getTotalLoan(), monthlyInterest, numberOfInstallments);
        prospect.setMonthlyPayment(Double.valueOf(String.format("%.2f", monthlyPayment)));
        return monthlyPayment;
    }
}
