package me.thursdayParty.safeFoodApi.social;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.account.Account;
import me.thursdayParty.safeFoodApi.account.AccountService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-22 [2019.11월.22]
 */


@Component
@RequiredArgsConstructor
public class SocialService {

    private final AccountService accountService;

    public UsernamePasswordAuthenticationToken doAuthentication(AccountConnection accountConnection) {

        if (!accountService.isExistAccount(accountConnection)) { // 신규 유저
            final Account account = accountService.signUpWithSocial(accountConnection);
            return setAuthenticationToken(account);
        }
//            final Account account = accountService.findBySocial(accountConnection);
//          return setAuthenticationToken(user);
        return null;
    }

    private UsernamePasswordAuthenticationToken setAuthenticationToken(Account user) {
        return new UsernamePasswordAuthenticationToken(user, null, getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}