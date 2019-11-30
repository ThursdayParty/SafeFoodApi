package me.thursdayParty.safeFoodApi.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.account.dto.AccountInfoResponseDto;
import me.thursdayParty.safeFoodApi.account.dto.AccountSaveRequestDto;
import me.thursdayParty.safeFoodApi.account.dto.AccountUpdateRequestDto;
import me.thursdayParty.safeFoodApi.account.dto.SocialAccountUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {

    private final AccountService accountService;

    @PutMapping
    public ResponseEntity update(Principal principal, @RequestBody AccountUpdateRequestDto accountUpdateRequestDto) {
        log.info("/api/account PUT :: user: {}, requestDto: {}", principal.getName(), accountUpdateRequestDto);

        String username = principal.getName();
        accountService.updateAccount(username, accountUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/social")
    public ResponseEntity update(Principal principal, @RequestBody SocialAccountUpdateRequestDto socialAccountUpdateRequestDto) {
        log.info("/api/account/social PUT :: user: {}, requestDto: {}", principal.getName(), socialAccountUpdateRequestDto);

        String username = principal.getName();
        accountService.updateAccount(username, socialAccountUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/currentUser")
    public ResponseEntity<AccountInfoResponseDto> current(Principal principal) {
        log.info("/api/account/currentUser GET :: user: {}", principal.getName());

        String username = principal.getName();
        AccountInfoResponseDto body = accountService.fetchAccountInfo(username);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody AccountSaveRequestDto accountSaveRequestDto) {
        System.out.println(accountSaveRequestDto);
        accountService.signUp(accountSaveRequestDto);
    }

    @GetMapping("/userIdDuplicationCheck")
    public ResponseEntity signUp(@RequestParam String accountId) {
        accountService.checkDuplication(accountId);
        return ResponseEntity.ok().build();
    }

}