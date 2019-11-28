package me.thursdayParty.safeFoodApi.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-27 [2019.11월.27]
 */

@Getter
@ToString
@NoArgsConstructor
public class SocialAccountUpdateRequestDto {
    private String name;
    private List<String> allergies = new ArrayList<>();
}
