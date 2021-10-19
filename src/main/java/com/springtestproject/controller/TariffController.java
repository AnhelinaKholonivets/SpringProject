package com.springtestproject.controller;

import com.springtestproject.dto.TariffDTO;
import com.springtestproject.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tariffs")
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping
    public String getAllTariffs(Model model) {
        model.addAttribute("tariffs", tariffService.getAllTariffs().getTariffs());
        return "tariff/allTariffs";
    }

    @GetMapping("/addTariff")
    public String addTariff() {
        return "tariff/addTariff";
    }

    @PostMapping("/addTariff")
    public String addNewTariff(@ModelAttribute("tariff") TariffDTO tariff) {
        tariffService.saveTariff(tariff);
        return "redirect:/tariffs";
    }

    @PutMapping("/update/{id}")
    public String updateTariff(@ModelAttribute("tariff") TariffDTO tariff, long id) {
        tariffService.updateTariff(tariff, id);
        return "tariff/updateTariff";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteTariff(@PathVariable Long id) {
        tariffService.deleteTariff(id);
        return "Deleted";
    }

}
