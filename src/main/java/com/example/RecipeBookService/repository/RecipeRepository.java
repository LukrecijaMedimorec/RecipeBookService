package com.example.RecipeBookService.repository;

import com.example.RecipeBookService.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findAllByAuthor(String author);

    List<RecipeEntity> findAllByContentContaining(String text);

}
