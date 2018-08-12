package guru.springframework.rest.api.v1.mapper.impl;

import org.springframework.stereotype.Component;
import guru.springframework.rest.api.v1.mapper.CategoryMapper;
import guru.springframework.rest.api.v1.model.CategoryDTO;
import guru.springframework.rest.domain.Category;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }

}
