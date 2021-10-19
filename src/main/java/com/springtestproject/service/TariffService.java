package com.springtestproject.service;

import com.springtestproject.dto.TariffDTO;
import com.springtestproject.dto.TariffsDTO;

public interface TariffService {
    TariffsDTO getAllTariffs();
    void saveTariff(TariffDTO tariff);
    void updateTariff(TariffDTO tariff, long id);
    void deleteTariff(Long id);
}
