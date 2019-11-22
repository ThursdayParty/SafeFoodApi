package me.thursdayParty.safeFoodApi.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUid(String uid);
}
