package me.thursdayParty.safeFoodApi.TakenFood;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TakenFood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long takenFoodId;

    private Long accountId;

    private Long foodId;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    public void init(Long accountId, Long foodId) {
        this.accountId = accountId;
        this.foodId = foodId;
    }

}