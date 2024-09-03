package com.example.RecipeBookService.service;

import com.example.RecipeBookService.entity.RecipeEntity;
import com.example.RecipeBookService.model.dto.AddRecipeDto;
import com.example.RecipeBookService.model.dto.RecipeDto;
import com.example.RecipeBookService.model.dto.mapper.RecipeMapper;
import com.example.RecipeBookService.model.exception.RecipeNotFoundException;
import com.example.RecipeBookService.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeEntity getRecipe(Long id) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);
        return recipeEntity.orElseThrow(() -> new RecipeNotFoundException("Recipe not found!"));
    }

    public List<RecipeDto> getAllRecipes() {
        List<RecipeEntity> recipeEntities = recipeRepository.findAll();
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (RecipeEntity recipeEntity : recipeEntities) {
            recipeDtos.add(RecipeMapper.fromEntity(recipeEntity));
        }
        return recipeDtos;
    }

    public RecipeDto addRecipe(RecipeEntity recipeEntity) {
        return RecipeMapper.fromEntity(recipeRepository.save(recipeEntity));
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public RecipeDto updateRecipe(Long id, AddRecipeDto recipeDto) {
        if (recipeRepository.existsById(id)) {
            return RecipeMapper.fromEntity(recipeRepository.save(RecipeMapper.fromDto(recipeDto, id)));

        } else throw new RecipeNotFoundException("Recipe not found!");
    }

    public List<RecipeDto> getAllRecipesByAuthor(String author) {
        List<RecipeEntity> recipeEntities = recipeRepository.findAllByAuthor(author);
        List<RecipeDto> recipeDtos = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntities) {
            recipeDtos.add(RecipeMapper.fromEntity(recipeEntity));
        }
        return recipeDtos;
    }

    public List<RecipeDto> getAllContaining(String text) {
        List<RecipeEntity> recipeEntities = recipeRepository.findAllByContentContaining(text);

        List<RecipeDto> recipeDtos = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntities) {
            recipeDtos.add(RecipeMapper.fromEntity(recipeEntity));
        }

        return recipeDtos;
    }

    public List<RecipeDto> search(String text) {
        List<RecipeEntity> recipeEntities = recipeRepository.searchRecipes(text);

        List<RecipeDto> recipeDtos = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntities) {
            recipeDtos.add(RecipeMapper.fromEntity(recipeEntity));
        }

        return recipeDtos;
    }

    public List<String> getAllAuthors() {
        List<RecipeEntity> recipeEntities = recipeRepository.findAll();
        List<String> authors = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntities) {
            authors.add(recipeEntity.getAuthor());
        }

        return authors.stream().distinct().collect(Collectors.toList());
    }
}
