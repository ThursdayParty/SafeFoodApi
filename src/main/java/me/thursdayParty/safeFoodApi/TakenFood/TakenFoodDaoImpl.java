package me.thursdayParty.safeFoodApi.TakenFood;

import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TakenFoodDaoImpl implements TakenFoodDao {

    @PersistenceContext
    private EntityManager em;

    private final String DTO_PATH = "me.thursdayParty.safeFoodApi.TakenFood.dto.";

    @Override
    public List<FetchAllTakenFoodResponseDto> findAllByAccountId(Long accountId) {
        String selectQuery =
                "select NEW "+DTO_PATH+"FetchAllTakenFoodResponseDto(tf, f)"+
                " from TakenFood tf inner join Food f on tf.foodId = f.foodId"
                ;

        TypedQuery<FetchAllTakenFoodResponseDto> query = em.createQuery(selectQuery, FetchAllTakenFoodResponseDto.class);

        List<FetchAllTakenFoodResponseDto> result = query.getResultList();
        return result;
    }

}
