package com.sundaegukbap.banchango.bookmark.domain;

import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_bookmarked_recipes")
public class RecipeBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    Recipe recipe;

    @Builder
    public RecipeBookmark(Long id, User user, Recipe recipe) {
        this.id = id;
        this.user = user;
        this.recipe = recipe;
    }

    @Builder
    public RecipeBookmark(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }
}
