package me.thursdayParty.safeFoodApi.account;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
    	Optional<Account> byUsername = memberRepository.findByUid(uid);
    	Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(uid));

        return new User(account.getUid(), account.getUpw(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    public Account readMember(String uid) {
    	return memberRepository.findByUid(uid).orElseThrow(()-> new UsernameNotFoundException(uid));
    }
}
