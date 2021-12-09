package com.springtestproject.controller;

import com.springtestproject.dto.TariffDto;
import com.springtestproject.service.ProductService;
import com.springtestproject.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tariffs")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TariffController {
    private final TariffService tariffService;
    private final ProductService productService;

    @Autowired
    public TariffController(TariffService tariffService, ProductService productService) {
        this.tariffService = tariffService;
        this.productService = productService;
    }

    @GetMapping("/addTariff")
    public String addTariff(Model model) {
        model.addAttribute("products", productService.findAllProducts().getProducts());
        return "tariff/addTariff";
    }

    @PostMapping("/addTariff")
    public String addNewTariff(@ModelAttribute("tariff") TariffDto tariff) {
        tariffService.saveTariff(tariff);
        return "redirect:/tariffs";
    }

    @PutMapping("/update/{id}")
    public String updateTariff(@ModelAttribute("tariff") TariffDto tariff, long id) {
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
