package guru.springframework.rest.api.v1.mapper;

import guru.springframework.rest.api.v1.model.CategoryDTO;
import guru.springframework.rest.domain.Category;

public interface CategoryMapper {
    CategoryDTO categoryToCategoryDTO(Category category);
}
