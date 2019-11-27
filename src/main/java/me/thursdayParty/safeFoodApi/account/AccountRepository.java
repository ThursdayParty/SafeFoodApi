package me.thursdayParty.safeFoodApi.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a JOIN FETCH a.allergies WHERE a.uid=:uid")
    Optional<Account> findByUid(@Param("uid") String uid);
}
