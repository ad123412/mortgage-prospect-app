package com.ada.mortgage.service;

import com.ada.mortgage.model.Prospect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Profile("!docker")
@Component
public class ProspectProcessorRunner implements CommandLineRunner {

    @Value("classpath:prospects.txt")
    private Resource prospectsFileFromClasspath;

    @Autowired
    private MortgageService mortgageService;

    @Override
    public void run(String... args) throws Exception {
        List<Prospect> prospects = new ArrayList<>();
        File prospectsFile = prospectsFileFromClasspath.getFile();
        BufferedReader br = new BufferedReader(new FileReader(prospectsFile));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine(); // just ignoring the header
        while ((line = br.readLine()) != null) {
            String[] customer = line.split(",");
            if (customer.length < 4) continue;
            Prospect prospect = new Prospect();
            // we should validate the input before setting to prospect
            if (customer[0].contains("\"") && customer[1].contains("\"")) {
                prospect.setCustomer(customer[0] + "," + customer[1]);
                prospect.setTotalLoan(Double.valueOf(customer[2]));
                prospect.setYearlyInterestRate(Double.valueOf(customer[3]));
                prospect.setNumberOfYears(Integer.valueOf(customer[4]));
            } else {
                prospect.setCustomer(customer[0]);
                prospect.setTotalLoan(Double.valueOf(customer[1]));
                prospect.setYearlyInterestRate(Double.valueOf(customer[2]));
                prospect.setNumberOfYears(Integer.valueOf(customer[3]));
            }
            prospects.add(prospect);
        }
        int i = 1;
        System.out.println("===============================================================");
        for (Prospect prospect : prospects) {
            //Prospect 1: CustomerName wants to borrow X € for a period of Z years and pay E € each month
            System.out.println("Prospect " + i++ + ":" + prospect.getCustomer() + " wants to borrow " +
                    String.format("%.2f", prospect.getTotalLoan()) + " € for a period of " + prospect.getNumberOfYears() +
                    " years and pay " + String.format("%.2f", mortgageService.calculateMonthlyPayment(prospect)) + " € each month");
        }
        System.out.println("===============================================================");
    }
}
