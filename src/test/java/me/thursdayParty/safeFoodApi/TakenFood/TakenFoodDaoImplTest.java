package me.thursdayParty.safeFoodApi.TakenFood;

import me.thursdayParty.safeFoodApi.qnaBoard.infra.QnaBoardDaoImpl;
import me.thursdayParty.safeFoodApi.qnaBoard.query.QnaBoardDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

}