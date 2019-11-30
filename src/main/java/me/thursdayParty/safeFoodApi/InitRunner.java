package me.thursdayParty.safeFoodApi;

import me.thursdayParty.safeFoodApi.TakenFood.TakenFoodRepository;
import me.thursdayParty.safeFoodApi.account.Account;
import me.thursdayParty.safeFoodApi.account.AccountRepository;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoardRepository;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.SaveQneBoardRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        qnaBoardRepository.save(new SaveQneBoardRequestDto("대두대두 ~ 대두 알러지때문에 너무 싫어 대두!!", "노이로제 걸릴 것 같아요 대두대두").toEntity("pacto"));
        qnaBoardRepository.save(new SaveQneBoardRequestDto("신라면 추천 많이 해주세요~~ 좋아여~~", "신라면 맛있따고요").toEntity("bactoria"));
        qnaBoardRepository.save(new SaveQneBoardRequestDto("와 라면 왜이렇게 칼로리가 높은건지..", "나트륨 좀 줄여야겠네요").toEntity("developer"));

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

        acc = new Account();
        acc.setUid("developer");
        acc.setUemail("testuser@gmail.com");
        acc.setUpw(passwordEncoder.encode("pas"));
        acc.setAllergies(new ArrayList<>(Arrays.asList("대두", "땅콩")));
        acc.setUname("쿠쿠크루");
        accountRepository.save(acc);

        acc = new Account();
        acc.setUid("google_105280303698738460000");
        acc.setUemail("testuser@gmail.com");
        acc.setUpw(passwordEncoder.encode("google"));
        acc.setAllergies(new ArrayList<>(Arrays.asList("대두", "땅콩")));
        acc.setUname("팍토");
        accountRepository.save(acc);

    }

}
