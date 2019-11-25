package me.thursdayParty.safeFoodApi.account;

import javax.servlet.http.HttpSession;

import me.thursdayParty.safeFoodApi.account.dto.AccountSaveRequestDto;
import me.thursdayParty.safeFoodApi.account.dto.AuthenticationRequest;
import me.thursdayParty.safeFoodApi.account.dto.AuthenticationTokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
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

	@GetMapping("/user")
    public Principal login(Principal principal) {
        System.out.println(principal);
	    return principal;
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody AccountSaveRequestDto accountSaveRequestDto) {
	    accountService.signUp(accountSaveRequestDto);
    }

}