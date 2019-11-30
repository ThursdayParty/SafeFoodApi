package me.thursdayParty.safeFoodApi.social;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.account.AccountService;
import me.thursdayParty.safeFoodApi.social.dto.AccessTokenRequestDto;
import me.thursdayParty.safeFoodApi.social.dto.AccessTokenResponseDto;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Collections;

/**
 * @author Bactoria
 * @since 2019-11-22 [2019.11월.22]
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/social")
public class SocialRestController {

    private final SocialService socialService;

    @PostMapping("/login")
    public ResponseEntity home(@RequestBody AccessTokenRequestDto accessTokenRequestDto) {
        log.info("/api/social/login POST :: requestDto: {}", accessTokenRequestDto);

        AccessTokenResponseDto body = socialService.login(accessTokenRequestDto);
        return ResponseEntity.ok().body(body);
    }

}
