package com.springtestproject.service.impl;

import com.springtestproject.dto.TariffDto;
import com.springtestproject.entity.Product;
import com.springtestproject.entity.Tariff;
import com.springtestproject.repository.TariffRepo;
import com.springtestproject.service.TariffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TariffServiceImpl implements TariffService {

    private final TariffRepo tariffRepo;

    @Autowired
    public TariffServiceImpl(TariffRepo tariffRepo) {
        this.tariffRepo = tariffRepo;
    }

    @Override
    public List<TariffDto> findAllTariffs() {
        return tariffRepo.findAll(Sort.by("product")).stream()
                .map(TariffDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void saveTariff(TariffDto tariff){
        try {
            Product product = new Product(Long.valueOf(tariff.getProduct()), null);
            Tariff tariffToSave = new Tariff(null, product, tariff.getTariff(), tariff.getPrice());
            tariffRepo.save(tariffToSave);
        } catch (Exception ex){
            log.info("{Tariff was not saved}");
        }
    }

    @Override
    public void updateTariff(TariffDto tariff, long id) {
        try {
            Tariff tariffToSave = new Tariff(id,null, tariff.getTariff(), tariff.getPrice());
            tariffRepo.save(tariffToSave);
        } catch (Exception ex){
            log.info("{Tariff was not saved}");
        }
    }

    @Override
    public void deleteTariff(long id) {
        try {
            tariffRepo.deleteById(id);
        } catch (Exception ex){
            log.info("{Tariff was not delete}");
        }
    }

    @Override
    public Page<TariffDto> findPaginated(Pageable pageable) {
        Page<Tariff> tariffPage = tariffRepo.findAll(pageable);

        List<TariffDto> tariffDtos = tariffPage
                .stream()
                .map(TariffDto::new)
                .collect(Collectors.toList());

        return new PageImpl<>(tariffDtos, pageable, tariffPage.getTotalElements());
    }
}