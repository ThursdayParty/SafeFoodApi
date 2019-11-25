package me.thursdayParty.safeFoodApi.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.thursdayParty.safeFoodApi.account.Account;

/**
 * @author Bactoria
 * @since 2019-11-25 [2019.11월.25]
 */

@Getter
@NoArgsConstructor
public class AccountSaveRequestDto {

    private String id;
    private String password;

    public Account toEntity() {
        Account a = new Account();
        a.setUid(this.id);
        a.setUpw(this.password);
        return a;
    }
}
