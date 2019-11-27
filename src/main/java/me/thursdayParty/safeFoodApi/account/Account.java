package me.thursdayParty.safeFoodApi.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import me.thursdayParty.safeFoodApi.account.dto.AccountUpdateRequestDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "uid")
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(unique = true, length=50)
	private String uid;

    @Column(length=200)
    private String upw;

    @Column(length=50)
    private String uname;

    @Column(length=50)
	private String uemail;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
	@Enumerated(EnumType.STRING)
	private AccountRole role;

    @ElementCollection
    @CollectionTable(name = "allergy", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "allgergy_name")
    private List<String> allergies = new ArrayList<>();

    public void updateUserInfo(String name, String password, List<String> allergies) {
        this.uname = name;
        this.upw = password;
        this.allergies = allergies;
    }
}