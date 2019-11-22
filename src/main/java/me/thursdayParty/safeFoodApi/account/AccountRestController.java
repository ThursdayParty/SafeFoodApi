package me.thursdayParty.safeFoodApi.account;

import javax.servlet.http.HttpSession;

import me.thursdayParty.safeFoodApi.account.dto.AuthenticationRequest;
import me.thursdayParty.safeFoodApi.account.dto.AuthenticationTokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AccountRestController {
	
	private final AuthenticationManager authenticationManager;
	private final AccountService memberService;
    
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
        
         Account account = memberService.readMember(username);
         System.out.println(account);
         return new AuthenticationTokenResponse(account.getUid(), account.getRole(), session.getId());
    }

	@GetMapping("/login")
    public Principal login(Principal principal) {
        System.out.println(principal);
	    return principal;
    }

}