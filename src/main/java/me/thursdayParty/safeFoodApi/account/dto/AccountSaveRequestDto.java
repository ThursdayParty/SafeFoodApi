package me.thursdayParty.safeFoodApi.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.account.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-25 [2019.11월.25]
 */

@Getter
@NoArgsConstructor
@ToString
public class AccountSaveRequestDto {

    private String id;
    private String password;
    private String name;
    private List<String> allergies;

    public Account toEntity() {
        Account a = new Account();
        a.setUid(this.id);
        a.setUpw(this.password);
        a.setUname(name);
        a.setAllergies(allergies);
        return a;
    }

}
