package com.springtestproject.service;

import com.springtestproject.dto.TariffDto;
import com.springtestproject.dto.TariffsDto;

public interface TariffService {
    TariffsDto getAllTariffs();
    void saveTariff(TariffDto tariff);
    void updateTariff(TariffDto tariff, long id);
    void deleteTariff(Long id);
}
