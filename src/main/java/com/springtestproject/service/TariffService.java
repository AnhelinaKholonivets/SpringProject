package com.springtestproject.service;

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

    public void saveNewTariff (Tariff tariff){
        try {
            tariffRepo.save(tariff);
        } catch (Exception ex){
            log.info("{Tariff not be saved}");
        }

    }

    public void updateTariff(Tariff tariff){
        try {
            tariffRepo.save(tariff);
        } catch (Exception ex){
            log.info("{Tariff update error}");
        }

    }
    public void deleteTariff(Tariff tariff){

    }

}
