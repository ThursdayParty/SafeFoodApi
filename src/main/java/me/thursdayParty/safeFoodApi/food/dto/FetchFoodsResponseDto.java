package me.thursdayParty.safeFoodApi.food.dto;

import lombok.Getter;
import me.thursdayParty.safeFoodApi.food.Food;

@Getter
public class FetchFoodsResponseDto {
	private long foodId;
	private String name;
	private String maker;
	private String materials;
	private String imageUrl;

	public FetchFoodsResponseDto(Food food) {
		this.foodId = food.getFoodId();
		this.name = food.getName();
		this.maker = food.getMaker();
		this.materials = food.getMaterials();
		this.imageUrl = food.getImageUrl();
	}
	
}
