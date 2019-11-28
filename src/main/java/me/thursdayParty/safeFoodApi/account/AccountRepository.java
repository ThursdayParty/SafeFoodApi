package me.thursdayParty.safeFoodApi.account;

import java.util.Optional;

import me.thursdayParty.safeFoodApi.account.dto.AccountInfoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.allergies WHERE a.uid=:uid")
    Optional<Account> findByUidWithAllergies(@Param("uid") String uid);

    Optional<Account> findByUid(String uid);

    @Query("SELECT new me.thursdayParty.safeFoodApi.account.dto.AccountInfoResponseDto(a) FROM Account a WHERE a.uid=:uid")
    Optional<AccountInfoResponseDto> findInfoByUid(@Param("uid") String uid);

}
