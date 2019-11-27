package me.thursdayParty.safeFoodApi.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EqualsAndHashCode(of = "foodId")
@NoArgsConstructor
@AllArgsConstructor
public class Food {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodId;

	@Column(columnDefinition = "TEXT")
	private String materials;

	@ColumnDefault("0")
    private Long views;

    @ColumnDefault("0")
    private Long recommendCount;

    private String name;
	private String maker;
	private String imageUrl;	

	private Integer bgn_year;
	private Integer serving_wt;
	private Double calorie;
	private Double carbohydrate;
	private Double protein;
	private Double fat;
	private Double sugars;
	private Double salt;
	private Double cholesterol;
	private Double saturated_fatty_acid;
	private Double trans_fat;

    public void visit() {
        this.views++;
    }
    public void recommend() {
        this.recommendCount++;
    }

}