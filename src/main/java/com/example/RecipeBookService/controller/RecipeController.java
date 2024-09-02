package com.example.RecipeBookService.controller;

import com.example.RecipeBookService.model.dto.AddRecipeDto;
import com.example.RecipeBookService.model.dto.RecipeDto;
import com.example.RecipeBookService.model.dto.mapper.RecipeMapper;
import com.example.RecipeBookService.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/1/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RecipeDto create(@RequestBody AddRecipeDto recipe) {
        return recipeService.addRecipe(RecipeMapper.fromDto(recipe));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RecipeDto getById(@PathVariable Long id) {
        return RecipeMapper.fromEntity(recipeService.getRecipe(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public RecipeDto update(@PathVariable Long id, @RequestBody AddRecipeDto recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @GetMapping("/findByAuthor/{author}")
    public List<RecipeDto> getAllRecipesByAuthor(@PathVariable String author) {
        return recipeService.getAllRecipesByAuthor(author);
    }

    @GetMapping("/contains/{text}")
    public List<RecipeDto> getAllRecipesContaining(@PathVariable String text) {
        return recipeService.getAllContaining(text);
    }

    @GetMapping("/getAllAuthors")
    public List<String> getAllAuthors() {
        return recipeService.getAllAuthors();
    }
}
