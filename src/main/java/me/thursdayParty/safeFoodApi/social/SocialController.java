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
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class SocialController {

    private final AccountService accountService;

    @GetMapping("/")
    public Principal home(Principal principal) {
        System.out.println(principal.getName());

        return principal;
    }

    @PostMapping("/api/login/google")
    public ResponseEntity home(@RequestBody AccessTokenRequestDto accessTokenRequestDto) {
        log.info("/api/login/google POST :: requestDto: {}", accessTokenRequestDto);

        accountService.saveSocialUser(accessTokenRequestDto);



        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(getMappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic YmFjdG9yaWE6cGFzc3dvcmQh");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String socialId = accessTokenRequestDto.getSocialType()+"_"+accessTokenRequestDto.getId();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","password");
        map.add("username", socialId);
        map.add("password",accessTokenRequestDto.getSocialType());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessTokenResponseDto> response =
                restTemplate.exchange("http://localhost:9090/oauth/token",
                        HttpMethod.POST,
                        entity,
                        AccessTokenResponseDto.class);

        AccessTokenResponseDto body = response.getBody();
        return ResponseEntity.ok().body(body);
    }

    private MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        return mappingJackson2HttpMessageConverter;
    }

}
