package com.springtestproject.service.impl;

import com.springtestproject.dto.TariffDTO;
import com.springtestproject.dto.TariffsDTO;
import com.springtestproject.entity.Product;
import com.springtestproject.entity.Tariff;
import com.springtestproject.repository.TariffRepo;
import com.springtestproject.service.TariffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TariffsDTO getAllTariffs() {
        //TODO checking for an empty user list
        return new TariffsDTO(tariffRepo.findAll());
    }

    @Override
    public void saveTariff(TariffDTO tariff) {

    }

//    @Override
//    public void saveTariff(TariffDTO tariff){
//        try {
//            //not work
//            Product product = new Product(tariff.getProduct(),null);
//            Tariff tariffToSave = new Tariff(null, product, tariff.getTariff(), tariff.getPrice());
//            tariffRepo.save(tariffToSave);
//        } catch (Exception ex){
//            log.info("{Tariff not be saved}");
//        }
//
//    }

    @Override
    public void updateTariff(TariffDTO tariff, long id) {
        try {
            Tariff tariffToSave = new Tariff(id, null, tariff.getTariff(), tariff.getPrice());
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
            log.info("{Tariff not be update}");
        }

    }
}
