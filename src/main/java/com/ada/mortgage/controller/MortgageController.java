package com.ada.mortgage.controller;

import com.ada.mortgage.model.Prospect;
import com.ada.mortgage.service.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("prospect")
public class MortgageController {

    @Autowired
    public MortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    private MortgageService mortgageService;

    private final List<Prospect> prospects = new ArrayList<>();

    @PostMapping
    public @ResponseBody Prospect createNewProspect(@RequestBody Prospect prospect) {
        prospect.setId(UUID.randomUUID().toString());
        mortgageService.calculateMonthlyPayment(prospect);
        prospects.add(prospect);
        return prospect;
    }

    @GetMapping
    public @ResponseBody List<Prospect> fetchAllProspects() {
        return prospects;
    }
}
