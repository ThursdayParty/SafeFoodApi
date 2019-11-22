package me.thursdayParty.safeFoodApi.qnaBoard.command;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.thursdayParty.safeFoodApi.food.Food;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.UpdateQnaBoardRequestDto;

@Builder 
@Entity
@Getter @Setter
@EqualsAndHashCode(of = "boardId")
@NoArgsConstructor
@AllArgsConstructor
public class QnaBoard {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	private String title;
	private String boardPassword;
	private String content;
	private String userId;
	private LocalDateTime createdTime;
	private Long views;

	@PrePersist
	void preInsert() {
	   if (this.createdTime == null) this.createdTime = LocalDateTime.now().plusHours(9L);
	   if (this.views == null) this.views = 0L;
	}
	
	public void updateBoard(UpdateQnaBoardRequestDto updateQnaBoardRequestDto) {
		this.title = updateQnaBoardRequestDto.getTitle();
		this.content = updateQnaBoardRequestDto.getContent();
	}

	public void visit() {
		this.views++;
	}
}
