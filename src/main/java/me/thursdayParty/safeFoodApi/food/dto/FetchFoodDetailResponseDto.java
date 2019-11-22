package me.thursdayParty.safeFoodApi.food.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.thursdayParty.safeFoodApi.food.Food;

@Getter
@NoArgsConstructor
public class FetchFoodDetailResponseDto {
	private long foodId;
	private String name;
	private String maker;
	private String materials;
	private String imageUrl;
	private Long views;

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

	private List<String> alergies = new ArrayList<>();

	public FetchFoodDetailResponseDto(Food food) {
		this.foodId = food.getFoodId();
		this.name = food.getName();
		this.maker = food.getMaker();
		this.materials = food.getMaterials();
		this.imageUrl = food.getImageUrl();
		this.views = food.getViews();
		this.bgn_year = food.getBgn_year();
		this.serving_wt = food.getServing_wt();
		this.calorie = food.getCalorie();
		this.carbohydrate = food.getCarbohydrate();
		this.protein = food.getProtein();
		this.fat = food.getFat();
		this.sugars = food.getSugars();
		this.salt = food.getSalt();
		this.cholesterol = food.getCholesterol();
		this.saturated_fatty_acid = food.getSaturated_fatty_acid();
		this.trans_fat = food.getTrans_fat();

		final String[] allergys = {"대두","땅콩","우유","게","새우","참치","연어","쑥","소고기","닭고기","돼지고기","복숭아","민들레","계란흰자"};
		for (String allergy : allergys) {
			if (materials.contains(allergy)) {
				alergies.add(allergy);
			}
		}
	}
	
}
