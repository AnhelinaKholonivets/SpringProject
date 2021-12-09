package com.springtestproject.service;

import com.springtestproject.dto.TariffDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TariffService {

    void saveTariff(TariffDto tariff);

    void updateTariff(TariffDto tariff, long id);

    void deleteTariff(long id);

    List<TariffDto> findAllTariffs();

    Page<TariffDto> findPaginated(Pageable pageable);
}
