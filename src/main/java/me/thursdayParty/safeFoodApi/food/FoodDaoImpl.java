package me.thursdayParty.safeFoodApi.food;

import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodsResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-26 [2019.11월.26]
 */

@Repository
public class FoodDaoImpl implements FoodDao {

    @PersistenceContext
    private EntityManager em;

    private final String DTO_PATH = "me.thursdayParty.safeFoodApi.food.dto.";

    @Override
    public List<FetchFoodsResponseDto> fetchBySearch(String searchType, String searchKeyword) {
        String selectQuery =
                "select NEW "+DTO_PATH+"FetchFoodsResponseDto(f)"+
                        " from Food f";
                ;

        if (searchType.equals("name")) {
            selectQuery += " where f.name like :searchKeyword";
        }
        if (searchType.equals("maker")) {
            selectQuery += " where f.maker like :searchKeyword";
        }
        if (searchType.equals("materials")) {
            selectQuery += " where f.materials like :searchKeyword";
        }
        TypedQuery<FetchFoodsResponseDto> query = em.createQuery(selectQuery, FetchFoodsResponseDto.class);
        query.setParameter("searchKeyword", "%"+searchKeyword.trim()+"%");

        return query.getResultList();
    }
}
