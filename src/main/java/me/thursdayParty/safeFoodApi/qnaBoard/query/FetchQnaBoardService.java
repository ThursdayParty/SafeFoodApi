package me.thursdayParty.safeFoodApi.qnaBoard.query;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoardService;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.PasswordCheckResponseDto;

@Service
@RequiredArgsConstructor
public class FetchQnaBoardService {

	private final QnaBoardDao dao;
	private final QnaBoardService service;
	
	public List<FetchAllQnaBoardResponseDto> fetchAllQnaBoards() {
		return dao.findAll();
	}

	public FetchDetailQnaBoardResponseDto fetchDetail(long boardId) {
		service.visit(boardId);
		FetchDetailQnaBoardResponseDto boardDetail = dao.findById(boardId);
		return boardDetail;
	}

	public PasswordCheckResponseDto isMatchPassword(long boardId, String boardPassword) {
		return dao.isMatchPassword(boardId, boardPassword);
	}

}
