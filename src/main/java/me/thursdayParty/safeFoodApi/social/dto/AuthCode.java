package me.thursdayParty.safeFoodApi.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Bactoria
 * @since 2019-11-24 [2019.11월.24]
 */

@ToString
@Getter
@NoArgsConstructor
public class AuthCode {
    String code;
    String redirectUri;
}
