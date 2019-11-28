package me.thursdayParty.safeFoodApi.social;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Bactoria
 * @since 2019-11-28 [2019.11월.28]
 */

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    private String grant_type;
    private String username;
    private String password;
}
