package com.example.RecipeBookService.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private List<String> tags;
}
