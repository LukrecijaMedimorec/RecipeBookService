package com.example.RecipeBookService.repository;

import com.example.RecipeBookService.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findAllByAuthor(String author);

    List<RecipeEntity> findAllByContentContaining(String text);

    @Query("SELECT a FROM RecipeEntity a WHERE " +
            "a.content LIKE %:searchText% OR " +
            "a.author LIKE %:searchText% OR " +
            "a.title LIKE %:searchText%")
    List<RecipeEntity> searchRecipes(@Param("searchText") String searchText);

}
