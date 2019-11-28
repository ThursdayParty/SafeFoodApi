package me.thursdayParty.safeFoodApi.social.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.account.Account;

/**
 * @author Bactoria
 * @since 2019-11-24 [2019.11월.24]
 */

@ToString
@Getter
@NoArgsConstructor
public class AccessTokenRequestDto {
    private String id;
    private String socialType;
    private String email;
    private String name;

    public Account toEntity() {
        Account a = new Account();
        String userId = socialType+"_"+id;
        a.setUpw(socialType);
        a.setUid(userId);
        a.setUemail(email);
        a.setUname(name);
        return a;
    }
}
