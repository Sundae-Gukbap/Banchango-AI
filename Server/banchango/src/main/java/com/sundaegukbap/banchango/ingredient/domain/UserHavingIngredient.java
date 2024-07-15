package com.sundaegukbap.banchango.ingredient.domain;

import com.sundaegukbap.banchango.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_having_ingredients")
@EntityListeners(AuditingEntityListener.class)
public class UserHavingIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime expriationDate;
    @Builder
    public UserHavingIngredient(Long id, User user, Ingredient ingredient, LocalDateTime createdAt, LocalDateTime expriationDate) {
        this.id = id;
        this.user = user;
        this.ingredient = ingredient;
        this.createdAt = createdAt;
        this.expriationDate = expriationDate;
    }
}
