package me.thursdayParty.safeFoodApi;

import me.thursdayParty.safeFoodApi.TakenFood.TakenFood;
import me.thursdayParty.safeFoodApi.TakenFood.TakenFoodDao;
import me.thursdayParty.safeFoodApi.TakenFood.TakenFoodRepository;
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

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitRunner implements ApplicationRunner {

    @Autowired
    TakenFoodRepository takenFoodRepository;
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
        acc.setAllergies(new ArrayList<>(Arrays.asList("대두", "땅콩")));
        acc.setUname("박토리아");
        accountRepository.save(acc);

        acc = new Account();
        acc.setUid("pacto");
        acc.setUemail("testuser@gmail.com");
        acc.setUpw(passwordEncoder.encode("pas"));
        acc.setAllergies(new ArrayList<>(Arrays.asList("대두", "땅콩")));
        acc.setUname("팍토");
        accountRepository.save(acc);

	}

}
