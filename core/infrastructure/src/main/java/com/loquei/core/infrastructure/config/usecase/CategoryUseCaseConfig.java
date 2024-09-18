package com.loquei.core.infrastructure.config.usecase;

import com.loquei.core.application.category.create.CreateCategoryUseCase;
import com.loquei.core.application.category.create.DefaultCreateCategoryUseCase;
import com.loquei.core.application.category.delete.DefaultDeleteCategoryUseCase;
import com.loquei.core.application.category.delete.DeleteCategoryUseCase;
import com.loquei.core.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.loquei.core.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.loquei.core.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.loquei.core.application.category.retrieve.list.ListCategoriesUseCase;
import com.loquei.core.application.category.update.DefaultUpdateCategoryUseCase;
import com.loquei.core.application.category.update.UpdateCategoryUseCase;
import com.loquei.core.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }
}
