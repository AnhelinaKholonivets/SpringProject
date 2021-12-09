package com.springtestproject;

import com.springtestproject.repository.TariffRepo;
import com.springtestproject.service.TariffService;
import com.springtestproject.service.impl.TariffServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TariffServiceImplTest {

    private final TariffRepo tariffRepo = Mockito.mock(TariffRepo.class);

    private final TariffService tariffService = new TariffServiceImpl(tariffRepo);

    @Test
    void deleteTariffThrowNullPointerExceptionThanIdIsNull(){
        Long id = null;

        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> tariffService.deleteTariff(id));

        assertNotNull(ex);
    }

}
