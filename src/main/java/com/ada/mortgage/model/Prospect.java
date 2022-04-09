package com.ada.mortgage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prospect {
    private String id;
    private String customer;
    private double totalLoan;
    private double yearlyInterestRate;
    private int numberOfYears;
    private double monthlyPayment;
}
