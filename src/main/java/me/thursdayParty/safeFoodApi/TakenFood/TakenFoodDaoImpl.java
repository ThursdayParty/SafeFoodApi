package me.thursdayParty.safeFoodApi.TakenFood;

import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.TakenFoodListDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class TakenFoodDaoImpl implements TakenFoodDao {

    @PersistenceContext
    private EntityManager em;

    private final String DTO_PATH = "me.thursdayParty.safeFoodApi.TakenFood.dto.";

    @Override
    public List<FetchAllTakenFoodResponseDto> findAllByAccountId(Long accountId) {
        String selectQuery =
                "select NEW "+DTO_PATH+"FetchAllTakenFoodResponseDto(tf, f)"+
                " from TakenFood tf inner join Food f on tf.foodId = f.foodId"+
                " where tf.accountId = :accountId"
                ;

        TypedQuery<FetchAllTakenFoodResponseDto> query = em.createQuery(selectQuery, FetchAllTakenFoodResponseDto.class);
        query.setParameter("accountId", accountId);

        List<FetchAllTakenFoodResponseDto> result = query.getResultList();
        return result;
    }

    @Override
    public List<DailyTakenFoodDto> findDailyTakenFoodByAccountId(Long accountId) {
        String selectQuery =
                "select NEW "+DTO_PATH+"TakenFoodListDto(tf, f)"+
                        " from TakenFood tf inner join Food f on tf.foodId = f.foodId"+
                        " where tf.accountId = :accountId"+
                            " and tf.createdDateTime >= :startDateTime"+
                            " and tf.createdDateTime <= :endDateTime"
                        ;

        final int DAY_COUNT = 10;

        TypedQuery<TakenFoodListDto> query = em.createQuery(selectQuery, TakenFoodListDto.class);
        query.setParameter("accountId", accountId);
        query.setParameter("startDateTime", LocalDateTime.of(LocalDate.now().minusDays(DAY_COUNT).plusDays(1), LocalTime.MIN));
        query.setParameter("endDateTime", LocalDateTime.now());

        List<TakenFoodListDto> result = query.getResultList();

        Map<LocalDate, List<TakenFoodListDto>> asd = result.stream()
                .collect(Collectors.groupingBy(tfld -> tfld.getTakenDateTime().toLocalDate()));


        LocalDate today = LocalDate.now();

        return reverseIntStreamRange(0,10)
                .mapToObj(today::minusDays)
                .map(date -> new DailyTakenFoodDto(date, asd.get(date)))
                .collect(Collectors.toList());
    }

    IntStream reverseIntStreamRange(int from, int to) {
        return IntStream.range(from, to).map(i -> to - i + from - 1);
    }
}
