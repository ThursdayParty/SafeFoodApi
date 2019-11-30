package me.thursdayParty.safeFoodApi.account;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.account.dto.AccountInfoResponseDto;
import me.thursdayParty.safeFoodApi.account.dto.AccountSaveRequestDto;
import me.thursdayParty.safeFoodApi.account.dto.AccountUpdateRequestDto;
import me.thursdayParty.safeFoodApi.account.dto.SocialAccountUpdateRequestDto;
import me.thursdayParty.safeFoodApi.social.dto.AccessTokenRequestDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUid(uid);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(uid));
        return new User(account.getUid(), account.getUpw(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    public Account readMember(String uid) {
        return accountRepository.findByUid(uid).orElseThrow(() -> new UsernameNotFoundException(uid));
    }

    public void saveSocialUser(AccessTokenRequestDto accessTokenRequestDto) {
        String userId = accessTokenRequestDto.getSocialType() + "_" + accessTokenRequestDto.getId();

        if (!accountRepository.findByUid(userId).isPresent()) {
            signUp(new AccountSaveRequestDto(userId, accessTokenRequestDto.getSocialType(), accessTokenRequestDto.getName()));
        }
    }

    public void signUp(AccountSaveRequestDto accountSaveRequestDto) {
        Account account = accountSaveRequestDto.toEntity();
        account.setUpw(passwordEncoder.encode(account.getUpw()));
        accountRepository.save(account);
    }

    public void checkDuplication(String accountId) {
        if (accountRepository.findByUid(accountId).isPresent()) {
            throw new RuntimeException("아이디가 이미 존재");
        }
    }

    public AccountInfoResponseDto fetchAccountInfo(String username) {
        return accountRepository.findInfoByUid(username)
                .orElseThrow(RuntimeException::new);
    }

    public void updateAccount(String username, AccountUpdateRequestDto accountUpdateRequestDto) {
        Account account = accountRepository.findByUid(username).orElseThrow(RuntimeException::new);

        String name = accountUpdateRequestDto.getName();
        String password = passwordEncoder.encode(accountUpdateRequestDto.getPassword());
        List<String> allergies = accountUpdateRequestDto.getAllergies();

        account.updateUserInfo(name, password, allergies);
    }

    public void updateAccount(String username, SocialAccountUpdateRequestDto socialAccountUpdateRequestDto) {
        Account account = accountRepository.findByUid(username).orElseThrow(() -> new RuntimeException("존재하지 않는 계정입니다."));

        if (!account.isSocialAccount()) {
            throw new RuntimeException("소셜 계정이 아닙니다.");
        }

        String name = socialAccountUpdateRequestDto.getName();
        List<String> allergies = socialAccountUpdateRequestDto.getAllergies();

        account.updateUserInfo(name, allergies);
    }
}
