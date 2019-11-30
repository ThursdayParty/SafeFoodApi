package me.thursdayParty.safeFoodApi.social;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.account.AccountService;
import me.thursdayParty.safeFoodApi.account.dto.AccountSaveRequestDto;
import me.thursdayParty.safeFoodApi.social.dto.AccessTokenRequestDto;
import me.thursdayParty.safeFoodApi.social.dto.AccessTokenResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Bactoria
 * @since 2019-11-30 [2019.11월.30]
 */

@Service
@RequiredArgsConstructor
public class SocialService {

    private final AccountService accountService;

    @Value("${url.login}")
    private String loginUrl;

    private MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        return mappingJackson2HttpMessageConverter;
    }

    public AccessTokenResponseDto login(AccessTokenRequestDto accessTokenRequestDto) {

        final String userId = accessTokenRequestDto.getSocialType() + "_" + accessTokenRequestDto.getId();
        final String password = accessTokenRequestDto.getSocialType();
        final String name = accessTokenRequestDto.getName();

        if (!accountService.isAlreadyExistUid(userId)) {
            accountService.signUp(new AccountSaveRequestDto(userId, password, name));
        }

        return fetchAccessToken(userId, accessTokenRequestDto.getSocialType());
    }

    private AccessTokenResponseDto fetchAccessToken(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(getMappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic YmFjdG9yaWE6cGFzc3dvcmQh");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", username);
        map.add("password", password);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessTokenResponseDto> response =
                restTemplate.exchange(loginUrl,
                        HttpMethod.POST,
                        entity,
                        AccessTokenResponseDto.class);

        return response.getBody();
    }
}
