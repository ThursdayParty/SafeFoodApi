package me.thursdayParty.safeFoodApi.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.account.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-27 [2019.11월.27]
 */

@Getter
@NoArgsConstructor
@ToString
public class AccountInfoResponseDto {
    private String name;
    private List<String> allergies = new ArrayList<>();

    public AccountInfoResponseDto(Account a) {
        this.name = a.getUname();
        this.allergies = a.getAllergies();
    }
}
