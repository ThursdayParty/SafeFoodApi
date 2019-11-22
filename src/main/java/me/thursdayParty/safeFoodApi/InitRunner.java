package me.thursdayParty.safeFoodApi;

import me.thursdayParty.safeFoodApi.account.Account;
import me.thursdayParty.safeFoodApi.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import me.thursdayParty.safeFoodApi.food.Food;
import me.thursdayParty.safeFoodApi.food.FoodRepository;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoardRepository;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.SaveQneBoardRequestDto;

@Component
public class InitRunner implements ApplicationRunner {

	@Autowired
	private QnaBoardRepository qnaBoardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		qnaBoardRepository.save(new SaveQneBoardRequestDto("제목1", "pass1", "내용1", "user1").toEntity());
		qnaBoardRepository.save(new SaveQneBoardRequestDto("제목2", "pass2", "내용2", "user2").toEntity());

		Account acc = new Account();
		acc.setUid("bactoria");
		acc.setUemail("bactoria@gmail.com");
		acc.setUpw(passwordEncoder.encode("pass"));
        accountRepository.save(acc);
	}


}
