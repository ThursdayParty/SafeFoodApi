package me.thursdayParty.safeFoodApi.qnaBoard.command;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

}
