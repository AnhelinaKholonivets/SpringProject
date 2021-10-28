package com.springtestproject.service;

import com.springtestproject.dto.TariffDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TariffService {
    List<TariffDto> getAllTariffs();
    void saveTariff(TariffDto tariff);
    void updateTariff(TariffDto tariff, long id);
    void deleteTariff(Long id);
    Page<TariffDto> findPaginated(Pageable pageable);
}
