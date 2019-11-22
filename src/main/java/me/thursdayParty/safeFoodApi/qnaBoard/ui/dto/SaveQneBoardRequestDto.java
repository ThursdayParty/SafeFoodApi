package me.thursdayParty.safeFoodApi.qnaBoard.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoard;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveQneBoardRequestDto {
	private String title;
	private String boardPassword;
	private String content;
	private String userId;

	public QnaBoard toEntity() {
		return QnaBoard.builder()
				.title(title)
				.boardPassword(boardPassword)
				.content(content)
				.userId(userId)
				.build();
	}
}
