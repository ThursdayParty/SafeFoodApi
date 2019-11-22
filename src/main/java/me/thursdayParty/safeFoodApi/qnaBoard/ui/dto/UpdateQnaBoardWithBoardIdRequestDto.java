package me.thursdayParty.safeFoodApi.qnaBoard.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQnaBoardWithBoardIdRequestDto {
	private long boardId;
	private String title;
	private String content;

	public UpdateQnaBoardWithBoardIdRequestDto(long boardId, UpdateQnaBoardRequestDto updateQnaBoardRequestDto) {
		this.boardId = boardId;
		this.title = updateQnaBoardRequestDto.getTitle();
		this.content = updateQnaBoardRequestDto.getContent();
	}
}
