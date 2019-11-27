package me.thursdayParty.safeFoodApi.account;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.account.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {
	
	private final AuthenticationManager authenticationManager;
	private final AccountService accountService;
    
	@PostMapping("/login")
    public AuthenticationTokenResponse login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
		System.out.println("/login POST");
         String username = authenticationRequest.getUsername();
         String password = authenticationRequest.getPassword();
        
         UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
         Authentication authentication = authenticationManager.authenticate(token);
         SecurityContextHolder.getContext().setAuthentication(authentication);
         System.out.println(token);

         session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                   SecurityContextHolder.getContext());
         System.out.println(token);

         Account account = accountService.readMember(username);
         System.out.println(account);
         return new AuthenticationTokenResponse(account.getUid(), account.getRole(), session.getId());
    }

    @PutMapping
    public ResponseEntity update(Principal principal, @RequestBody AccountUpdateRequestDto accountUpdateRequestDto) {
        log.info("/api/account PUT :: user: {}, requestDto: {}", principal.getName(), accountUpdateRequestDto);

        String username = principal.getName();
        accountService.updateAccount(username, accountUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/currentUser")
    public ResponseEntity<AccountInfoResponseDto> current(Principal principal) {
        log.info("/api/account/current GET :: user: {}", principal.getName());

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