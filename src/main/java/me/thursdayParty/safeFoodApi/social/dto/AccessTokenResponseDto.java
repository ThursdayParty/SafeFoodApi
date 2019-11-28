package me.thursdayParty.safeFoodApi.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Bactoria
 * @since 2019-11-28 [2019.11월.28]
 */

@Getter
@NoArgsConstructor
@ToString
public class AccessTokenResponseDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
}
