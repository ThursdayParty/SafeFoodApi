package me.thursdayParty.safeFoodApi.qnaBoard.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoard;

@ToString
@Getter
@NoArgsConstructor
public class SaveQneBoardRequestDto {
	private String title;
    private String content;
    private String userId;

    public SaveQneBoardRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public QnaBoard toEntity(String userId) {
		return QnaBoard.builder()
				.title(title)
				.content(content)
                .userId(userId)
				.build();
	}
}
