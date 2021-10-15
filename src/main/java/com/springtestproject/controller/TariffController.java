package com.springtestproject.controller;

import com.springtestproject.entity.Tariff;
import com.springtestproject.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/tariff")
    public String tariff() {
        return "tariff";
    }

    @GetMapping("/tariffs")
    public String getAllUser(Model model){
        model.addAttribute("tariffs", tariffService.getAllTariffs().getTariffs());
        return "allTariffs";
    }

    @GetMapping("/tariffs/addTariff")
    public String addTariff() {
        return "addTariff";
    }

    @PostMapping( "/tariffs/addTariff")
    public String addNewTariff(@ModelAttribute("tariff") Tariff tariff) {
        Tariff tariffToSave = new Tariff(null, tariff.getName(), tariff.getPrice());
        tariffService.saveNewTariff(tariffToSave);
        return "redirect:/tariffs";
    }

    @GetMapping("/deleteTariff")
    public String deleteTariff(Model model){
        return "redirect:/tariffs";
    }

}
