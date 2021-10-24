package com.springtestproject.service.impl;

import com.springtestproject.dto.TariffDto;
import com.springtestproject.dto.TariffsDto;
import com.springtestproject.entity.Product;
import com.springtestproject.entity.Tariff;
import com.springtestproject.repository.TariffRepo;
import com.springtestproject.service.TariffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TariffServiceImpl implements TariffService {

    private final TariffRepo tariffRepo;

    @Autowired
    public TariffServiceImpl(TariffRepo tariffRepo) {
        this.tariffRepo = tariffRepo;
    }

    @Override
    public TariffsDto getAllTariffs() {
        //TODO checking for an empty user list
        return new TariffsDto(tariffRepo.findAll(Sort.by("product")));
    }

    @Override
    public void saveTariff(TariffDto tariff){
        try {
            Product product = new Product(Long.valueOf(tariff.getProduct()), null);
            Tariff tariffToSave = new Tariff(null, product, tariff.getTariff(), tariff.getPrice());
            tariffRepo.save(tariffToSave);
        } catch (Exception ex){
            log.info("{Tariff not be saved}");
        }
    }

    @Override
    public void updateTariff(TariffDto tariff, long id) {
        try {
            Tariff tariffToSave = new Tariff(id,null, tariff.getTariff(), tariff.getPrice());
            tariffRepo.save(tariffToSave);
        } catch (Exception ex){
            log.info("{Tariff not be update}");
        }
    }

    @Override
    public void deleteTariff(Long id) {
        try {
            tariffRepo.deleteById(id);
        } catch (Exception ex){
            log.info("{Tariff not be delete}");
        }

    }
}
