package me.thursdayParty.safeFoodApi.TakenFood;

import me.thursdayParty.safeFoodApi.TakenFood.dto.TakenFoodListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Bactoria
 * @since 2019-11-25 [2019.11월.25]
 */

@SpringBootTest
class TakenFoodDaoImplTest {

    @Autowired
    TakenFoodDao takenFoodDao;

    @Test
    public void xptmxm() {
        System.out.println(takenFoodDao.findAllByAccountId(1L));
    }

    @Test
    public void xptmxm2() {

        List asd = takenFoodDao.findDailyTakenFoodByAccountId(2L);
        System.out.println(asd);

    }

}