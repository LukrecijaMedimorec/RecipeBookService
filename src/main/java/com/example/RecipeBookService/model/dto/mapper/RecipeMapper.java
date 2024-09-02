package com.example.RecipeBookService.model.dto.mapper;

import com.example.RecipeBookService.entity.RecipeEntity;
import com.example.RecipeBookService.model.dto.AddRecipeDto;
import com.example.RecipeBookService.model.dto.RecipeDto;

public class RecipeMapper {

    public static RecipeDto fromEntity(final RecipeEntity recipeEntity) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipeEntity.getId());
        recipeDto.setContent(recipeEntity.getContent());
        recipeDto.setAuthor(recipeEntity.getAuthor());
        recipeDto.setTags(recipeEntity.getTags());
        return recipeDto;
    }

    public static RecipeEntity fromDto(final AddRecipeDto addRecipeDto) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setContent(addRecipeDto.getContent());
        recipeEntity.setAuthor(addRecipeDto.getAuthor());
        recipeEntity.setTags(addRecipeDto.getTags());
        return recipeEntity;
    }

    public static RecipeEntity fromDto(final AddRecipeDto addRecipeDto, final Long id) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(id);
        recipeEntity.setContent(addRecipeDto.getContent());
        recipeEntity.setAuthor(addRecipeDto.getAuthor());
        recipeEntity.setTags(addRecipeDto.getTags());
        return recipeEntity;
    }
}
