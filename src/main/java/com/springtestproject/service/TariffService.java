package com.springtestproject.service;

import com.springtestproject.dto.TariffDTO;
import com.springtestproject.dto.TariffsDTO;
import com.springtestproject.entity.Tariff;
import com.springtestproject.repository.TariffRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TariffService {

    private final TariffRepo tariffRepo;

    @Autowired
    public TariffService(TariffRepo tariffRepo) {
        this.tariffRepo = tariffRepo;
    }

    public TariffsDTO getAllTariffs() {
        //TODO checking for an empty user list
        return new TariffsDTO(tariffRepo.findAll());
    }

    public void saveTariff(TariffDTO tariff){
        try {
            Tariff tariffToSave = new Tariff(null, tariff.getName(), tariff.getPrice());
            tariffRepo.save(tariffToSave);
        } catch (Exception ex){
            log.info("{Tariff not be saved}");
        }

    }

    public void updateTariff(TariffDTO tariff, long id) {
        try {
            Tariff tariffToSave = new Tariff(id, tariff.getName(), tariff.getPrice());
            tariffRepo.save(tariffToSave);
        } catch (Exception ex){
            log.info("{Tariff not be update}");
        }

    }

    public void deleteTariff(Long id) {
        try {
            tariffRepo.deleteById(id);
        } catch (Exception ex){
            log.info("{Tariff not be update}");
        }

    }
}
