package com.example.RecipeBookService.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddRecipeDto {
    private String title;
    private String content;
    private String author;
    private List<String> tags;
}
