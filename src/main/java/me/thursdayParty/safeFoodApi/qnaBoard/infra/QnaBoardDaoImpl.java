package me.thursdayParty.safeFoodApi.qnaBoard.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import me.thursdayParty.safeFoodApi.qnaBoard.query.QnaBoardDao;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.PasswordCheckResponseDto;

@Repository
public class QnaBoardDaoImpl implements QnaBoardDao {

	@PersistenceContext
    private EntityManager em;

    private final String DTO_PATH = "me.thursdayParty.safeFoodApi.qnaBoard.query.dto.";
    
    @Override
    public List<FetchAllQnaBoardResponseDto> findAll() {
        String selectQuery =
                "select NEW "+DTO_PATH+"FetchAllQnaBoardResponseDto(qb.boardId, qb.title, a.uname, qb.createdTime, qb.views)"+
                " from QnaBoard qb inner join Account a on qb.userId=a.uid";

        TypedQuery<FetchAllQnaBoardResponseDto> query = em.createQuery(selectQuery, FetchAllQnaBoardResponseDto.class);

        return query.getResultList();
    }

	@Override
	public FetchDetailQnaBoardResponseDto findQnaBoardDetailByBoardId(long boardId) {
        String selectQuery =
                "select NEW "+DTO_PATH+"FetchDetailQnaBoardResponseDto(qb.boardId, qb.title, qb.content, a.uid, a.uname, qb.createdTime, qb.views)"+
                " from QnaBoard qb inner join Account a on qb.userId=a.uid"+
                " where qb.boardId = :boardId";

        TypedQuery<FetchDetailQnaBoardResponseDto> query = em.createQuery(selectQuery, FetchDetailQnaBoardResponseDto.class);
        query.setMaxResults(1);
        query.setParameter("boardId", boardId);
        
        return query.getSingleResult();
	}
	
	@Override
	public PasswordCheckResponseDto isMatchPassword(long boardId, String boardPassword) {
        String selectQuery =
                "select count(*)"+
                " from QnaBoard qb"+
                " where qb.boardId = :boardId and qb.boardPassword = :boardPassword";

        TypedQuery<Long> query = em.createQuery(selectQuery, Long.class);
        query.setParameter("boardId", boardId);
        query.setParameter("boardPassword", boardPassword);
        
        long result = query.getSingleResult();

        if(result != 1) {
        	return new PasswordCheckResponseDto(false);
        }        
        return new PasswordCheckResponseDto(true);
	}

}
